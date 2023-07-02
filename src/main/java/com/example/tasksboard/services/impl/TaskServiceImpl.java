package com.example.tasksboard.services.impl;

import com.example.tasksboard.dto.TaskDto;
import com.example.tasksboard.entities.Board;
import com.example.tasksboard.entities.Task;
import com.example.tasksboard.entities.UserTask;
import com.example.tasksboard.models.Role;
import com.example.tasksboard.repositories.BoardRepository;
import com.example.tasksboard.repositories.TaskRepository;
import com.example.tasksboard.repositories.UserRepository;
import com.example.tasksboard.services.TaskService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    @Override
    public void addTask(TaskDto taskDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Task task = modelMapper.map(taskDto, Task.class);
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        UserTask user = userRepository.findByEmail(getAuthentication().getName());
        Task taskFind = taskRepository.findById(id).get();
        if (user.getRole().equals(Role.ADMIN) || taskFind.getOwner().getId() == user.getId()) {
            taskRepository.deleteById(id);
        }
    }

    @Override
    public void update(TaskDto taskDto) {
        UserTask user = userRepository.findByEmail(getAuthentication().getName());
        Task taskFind = taskRepository.findById(taskDto.getId()).get();
        if (user.getRole().equals(Role.ADMIN) || taskFind.getOwner().getId() == user.getId()) {
            Task task = new Task();
            task.setStateTask(taskDto.getStateTask());
            task.setId(taskDto.getId());
            task.setText(taskDto.getText());
            UserTask userTask = modelMapper.map(taskDto.getOwner(), UserTask.class);
            task.setOwner(userTask);
            task.setDateOfCreation(taskDto.getDateOfCreation());
            taskRepository.save(task);
        }
    }

    @Override
    public List<TaskDto> getAllTasks(Long boardId) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserTask user = userRepository.findByEmail(getAuthentication().getName());
        List<TaskDto> tasks = new ArrayList<>();
        if (user.getRole().equals(Role.ADMIN) || user.getBoardsId().contains(boardId)) {
            Board board = boardRepository.findById(boardId).get();
            tasks = board.getBoardsId().stream().map(task -> modelMapper.map(task, TaskDto.class)).collect(Collectors.toList());
        }
        return tasks;
    }

    @Override
    public void deleteAllTasks() {
        UserTask user = userRepository.findByEmail(getAuthentication().getName());
        if (user.getRole().equals(Role.ADMIN)) {
            taskRepository.deleteAll();
        }

        else {
            taskRepository.deleteTasks(user.getId());
        }
    }
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
