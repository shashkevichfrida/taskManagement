package com.example.tasksboard.controllers;

import com.example.tasksboard.dto.UserTaskDto;
import com.example.tasksboard.entities.UserTask;
import com.example.tasksboard.services.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@Tag(name = "user", description = "user APIs")
@AllArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PreAuthorize("hasAuthority('developers:write')")
    @PostMapping("/save")
    public void save(@RequestBody UserTaskDto userTaskDto){
        userService.save(userTaskDto);
    }

    @PostMapping("/update")
    public void update(@RequestBody UserTaskDto userTaskDto){
        userService.update(userTaskDto);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(@Param("id") Long id){
        userService.deleteById(id);
    }
}
