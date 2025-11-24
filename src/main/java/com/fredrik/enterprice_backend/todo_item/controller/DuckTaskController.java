package com.fredrik.enterprice_backend.todo_item.controller;

import com.fredrik.enterprice_backend.todo_item.service.DuckTaskService;
import com.fredrik.enterprice_backend.user.service.DuckService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ducktasks")
@AllArgsConstructor
public class DuckTaskController {
    private final DuckTaskService duckTaskService;



}
