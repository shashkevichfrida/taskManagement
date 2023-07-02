package com.example.tasksboard.services;

import com.example.tasksboard.dto.BoardDto;
import com.example.tasksboard.dto.UserTaskDto;
import com.example.tasksboard.repositories.UserRepository;
import org.springframework.stereotype.Service;


public interface UserService {

    void save(UserTaskDto userTaskDto);

    void update(UserTaskDto userTaskDto);

    void deleteById(Long id);

    void addUserToBoard(UserTaskDto userTaskDto, BoardDto boardDto);
    void deleteUserFromBoard(UserTaskDto userTaskDto, BoardDto boardDto);
}
