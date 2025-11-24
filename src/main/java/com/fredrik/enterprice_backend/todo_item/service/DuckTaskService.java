package com.fredrik.enterprice_backend.todo_item.service;

import com.fredrik.enterprice_backend.todo_item.dto.CreateDuckTaskDTO;
import com.fredrik.enterprice_backend.user.dto.responseDuckDTO;

import java.util.UUID;

public interface DuckTaskService {
    //? Create a a new Ducktask
    // Get back a responseDTO
    responseDuckDTO createDuckTask(CreateDuckTaskDTO createDuckTaskDTO);

    //? Find a Ducktask by id
    responseDuckDTO findDuckTaskById(UUID id);

    //? Delete a Ducktask by id
    void deleteDuckTaskById(UUID id);

    //? Update a Ducktask by id
    responseDuckDTO updateDuckTaskById(UUID id, CreateDuckTaskDTO createDuckTaskDTO);

    //? Find all Ducktasks by DuckId
    responseDuckDTO findAllTaskByCurrentDuck(UUID duckId);

    //? Mark a Ducktask as completed
    responseDuckDTO markDuckTaskAsCompleted(UUID id);
}
