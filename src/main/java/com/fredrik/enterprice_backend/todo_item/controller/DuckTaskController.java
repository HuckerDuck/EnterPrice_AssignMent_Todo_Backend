package com.fredrik.enterprice_backend.todo_item.controller;

import com.fredrik.enterprice_backend.todo_item.dto.CreateDuckTaskDTO;
import com.fredrik.enterprice_backend.todo_item.dto.ResponseDuckTaskDTO;
import com.fredrik.enterprice_backend.todo_item.dto.UpdateDuckTaskDTO;
import com.fredrik.enterprice_backend.todo_item.service.DuckTaskService;
import com.fredrik.enterprice_backend.user.service.DuckService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ducktasks")
@AllArgsConstructor
public class DuckTaskController {
    private final DuckTaskService duckTaskService;

    //? 1 - Add a Duck with a post
    //? Post -> /api/ducktasks/create
    @PostMapping
    public ResponseEntity<ResponseDuckTaskDTO> createDuckTask(
            @Valid @RequestBody CreateDuckTaskDTO createDuckTaskDTO){
        System.out.println("Controller received:");
        System.out.println("It got the DTO " + createDuckTaskDTO);

        ResponseDuckTaskDTO createdDuckTask = duckTaskService.createDuckTask(createDuckTaskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDuckTask);
    }

    //? 2 - Get all tasks for the current Duck
    //? Get -> /api/ducktasks/current
    @GetMapping
    public ResponseEntity<List<ResponseDuckTaskDTO>> findAllTaskByCurrentDuck(){
        List<ResponseDuckTaskDTO> allDuckTasks = duckTaskService.findAllTaskByCurrentDuck();
        return ResponseEntity.ok(allDuckTasks);
    }

    //? 3 - Read a specific task by id
    //? Get -> /api/tasks/{id}
    @GetMapping("/{id}")
    public ResponseEntity <ResponseDuckTaskDTO> getTaskById(
            @PathVariable UUID id
    ){
        ResponseDuckTaskDTO task = duckTaskService.findDuckTaskById(id);
        return ResponseEntity.ok(task);
    }

    //? 4 - Update a specific task by id
    //? Put -> /api/ducktasks/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity <ResponseDuckTaskDTO> updateTaskById(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateDuckTaskDTO updateDuckTaskDTO
    ){
        ResponseDuckTaskDTO updatedTask = duckTaskService.updateDuckTaskById(id, updateDuckTaskDTO);
        return ResponseEntity.ok(updatedTask);
    }

    //? 5 - Delete a specific task by id
    //? Delete -> /api/tasks/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable UUID id){
        duckTaskService.deleteDuckTaskById(id);
        return ResponseEntity.noContent().build();
    }

    //? 6 - Mark a specific task as completed
    //? Put -> /api/tasks/{id}/complete
    @PatchMapping("/completedtask/{id}")
    public ResponseEntity<ResponseDuckTaskDTO> markTaskAsCompleted(
            @PathVariable UUID id){
        ResponseDuckTaskDTO completedTask = duckTaskService.markDuckTaskAsCompleted(id);
        return ResponseEntity.ok(completedTask);
    }






}
