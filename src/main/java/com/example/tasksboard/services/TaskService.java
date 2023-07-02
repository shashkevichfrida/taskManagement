package com.example.tasksboard.services;

import com.example.tasksboard.dto.TaskDto;

import java.util.List;

public interface TaskService {
    void addTask(TaskDto taskDto);
    void deleteTask(Long id);
    void update(TaskDto taskDto);
    List<TaskDto> getAllTasks(Long boardId);
    void deleteAllTasks();
}
