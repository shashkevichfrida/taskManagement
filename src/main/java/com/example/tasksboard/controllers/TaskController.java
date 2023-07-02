package com.example.tasksboard.controllers;

import com.example.tasksboard.dto.TaskDto;
import com.example.tasksboard.services.impl.TaskServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@Tag(name = "task", description = "task APIs")
@PreAuthorize("hasAnyAuthority('developers:read', 'developers:write')")
@AllArgsConstructor
public class TaskController {

    private final TaskServiceImpl taskService;

    @PostMapping("/saveTask")
    public void save(@RequestBody TaskDto taskDto){
        taskService.addTask(taskDto);
    }

    @DeleteMapping("/deleteTask")
    public void deleteTask(@RequestBody Long id){
        taskService.deleteTask(id);
    }

    @DeleteMapping("/deleteAllTasks")
    public void deleteAllTasks(){
        taskService.deleteAllTasks();
    }


    @PostMapping("/updateTask")
    public void updateTask(@RequestBody TaskDto taskDto){
        taskService.update(taskDto);
    }

    @PostMapping("/getAllTasks")
    public List<TaskDto> getAllBoards(Long boardId){
        return taskService.getAllTasks(boardId);
    }
}
