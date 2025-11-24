package com.fredrik.enterprice_backend.todo_item.service;

import com.fredrik.enterprice_backend.todo_item.dto.CreateDuckTaskDTO;
import com.fredrik.enterprice_backend.todo_item.dto.ResponseDuckTaskDTO;
import com.fredrik.enterprice_backend.todo_item.dto.UpdateDuckTaskDTO;
import com.fredrik.enterprice_backend.user.dto.responseDuckDTO;

import java.util.List;
import java.util.UUID;

public interface DuckTaskService {
    //? Create a a new Ducktask
    // Get back a responseDTO
    ResponseDuckTaskDTO createDuckTask(CreateDuckTaskDTO createDuckTaskDTO);

    //? Find a Ducktask by id
    ResponseDuckTaskDTO findDuckTaskById(UUID id);

    //? Delete a Ducktask by id
    void deleteDuckTaskById(UUID id);

    ResponseDuckTaskDTO updateDuckTaskById(UUID id, UpdateDuckTaskDTO updateDuckTaskDTO);

    //? Find all Ducktasks by DuckId
    List<ResponseDuckTaskDTO> findAllTaskByCurrentDuck();

    //? Mark a Ducktask as completed
    ResponseDuckTaskDTO markDuckTaskAsCompleted(UUID id);
}
