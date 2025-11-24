package com.fredrik.enterprice_backend.todo_item.service;

import com.fredrik.enterprice_backend.todo_item.dto.CreateDuckTaskDTO;
import com.fredrik.enterprice_backend.todo_item.mapper.DuckTaskMapper;
import com.fredrik.enterprice_backend.todo_item.repository.DuckTaskRepository;
import com.fredrik.enterprice_backend.user.dto.responseDuckDTO;
import com.fredrik.enterprice_backend.user.duckdetails_aka_userdetails.DuckDetailsService;
import com.fredrik.enterprice_backend.user.model.Duck;
import com.fredrik.enterprice_backend.user.repository.DuckRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
    public responseDuckDTO createDuckTask(CreateDuckTaskDTO createDuckTaskDTO) {
        return null;
    }

    @Override
    public responseDuckDTO findDuckTaskById(UUID id) {
        return null;
    }

    @Override
    public void deleteDuckTaskById(UUID id) {

    }

    @Override
    public responseDuckDTO updateDuckTaskById(UUID id, CreateDuckTaskDTO createDuckTaskDTO) {
        return null;
    }

    @Override
    public responseDuckDTO findAllTaskByCurrentDuck(UUID duckId) {
        return null;
    }

    @Override
    public responseDuckDTO markDuckTaskAsCompleted(UUID id) {
        return null;
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
