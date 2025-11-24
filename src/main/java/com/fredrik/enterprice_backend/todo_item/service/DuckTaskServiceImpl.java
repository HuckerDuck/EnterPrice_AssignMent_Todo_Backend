package com.fredrik.enterprice_backend.todo_item.service;

import com.fredrik.enterprice_backend.todo_item.dto.CreateDuckTaskDTO;
import com.fredrik.enterprice_backend.todo_item.dto.ResponseDuckTaskDTO;
import com.fredrik.enterprice_backend.todo_item.dto.UpdateDuckTaskDTO;
import com.fredrik.enterprice_backend.todo_item.mapper.DuckTaskMapper;
import com.fredrik.enterprice_backend.todo_item.model.DuckTask;
import com.fredrik.enterprice_backend.todo_item.repository.DuckTaskRepository;
import com.fredrik.enterprice_backend.user.model.Duck;
import com.fredrik.enterprice_backend.user.repository.DuckRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

//? This is the service layer for the DuckTask
// In here we will add method for the crud operations for the
// DuckTask and then we will add them into DuckTaskServiceImpl
@Service
@AllArgsConstructor
@Transactional
public class DuckTaskServiceImpl implements DuckTaskService{
    private final DuckTaskRepository duckTaskRepository;
    private final DuckRepository duckRepository;
    private final DuckTaskMapper duckTaskMapper;

    @Override
    public ResponseDuckTaskDTO createDuckTask(CreateDuckTaskDTO createDuckTaskDTO) {
        //? Get the current duck from the SecurityContext
        Duck currentDuck = getCurrentDuck();

        //? Then map it from a DTO to an Entity
        DuckTask duckTask = duckTaskMapper.toEntity(createDuckTaskDTO, currentDuck);

        //? Save the entity (aka DuckTask) to the database
        DuckTask savedDuckTask = duckTaskRepository.save(duckTask);

        //? Then map the Entity to the ResponseDTO and return it
        return duckTaskMapper.toResponseDTO(savedDuckTask);
    }

    @Override
    public ResponseDuckTaskDTO findDuckTaskById(UUID id) {
        //? Get the current duck from the SecurityContext
        Duck currentDuck = getCurrentDuck();

        //? Get the DuckTask or throw an Exception
        DuckTask duckTask = duckTaskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DuckTask not found with id: " + id));

        //? Check if the DuckTask belongs to the current Duck
        if (!duckTask.getDuck().equals(currentDuck)){
            throw new RuntimeException("You are not allowed to access this DuckTask");
        }

        return duckTaskMapper.toResponseDTO(duckTask);
    }

    @Override
    public void deleteDuckTaskById(UUID id) {
        Duck currentDuck = getCurrentDuck();

        DuckTask duckTask = duckTaskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DuckTask not found with id: " + id));

        if (!duckTask.getDuck().equals(currentDuck)){
            throw new RuntimeException("You are not allowed to delete this DuckTask");
        }

        duckTaskRepository.deleteById(id);

    }

    @Override
    public ResponseDuckTaskDTO updateDuckTaskById(UUID id, UpdateDuckTaskDTO updateDuckTaskDTO) {
        Duck currentDuck = getCurrentDuck();

        DuckTask duckTask = duckTaskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DuckTask not found with id: " + id));

        if (!duckTask.getDuck().equals(currentDuck)){
            throw new RuntimeException("You are not allowed to update this DuckTask");
        }

        //? Update the DuckTask with the new values
        //? This is with the
        duckTask.setTitle(updateDuckTaskDTO.title());
        duckTask.setDescription(updateDuckTaskDTO.description());
        duckTask.setCompleted(updateDuckTaskDTO.completed());
        duckTask.setPriority(updateDuckTaskDTO.priority());

        return duckTaskMapper.toResponseDTO(duckTaskRepository.save(duckTask));
    }

    @Override
    public List <ResponseDuckTaskDTO> findAllTaskByCurrentDuck() {
        Duck currentDuck = getCurrentDuck();

        //? Get all task that belongs to this Duck
        List<DuckTask> duckTasks = duckTaskRepository.findAllByDuckId(currentDuck.getId());

        //? Map them to the ResponseDTO and return them
        return duckTasks.stream()
                .map(duckTaskMapper::toResponseDTO)
                .toList();
    }

    @Override
    public ResponseDuckTaskDTO markDuckTaskAsCompleted(UUID id) {
        Duck currentDuck = getCurrentDuck();

        DuckTask duckTask = duckTaskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DuckTask not found with id: " + id));

        if (!duckTask.getDuck().equals(currentDuck)){
            throw new RuntimeException("You are not allowed to mark this DuckTask as completed");
        }

        duckTask.setCompleted(true);

        return duckTaskMapper.toResponseDTO(duckTaskRepository.save(duckTask));
    }

    //? Helping method from the SecurityContext
    //? Gonna find and see if a user is logged in or not
    private Duck getCurrentDuck(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getName() == null){
            throw new RuntimeException("User is not logged in");
        }

        String username = authentication.getName();
        return duckRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }
}
