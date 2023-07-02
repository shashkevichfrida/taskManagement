package com.example.tasksboard.services.impl;

import com.example.tasksboard.dto.BoardDto;
import com.example.tasksboard.dto.UserTaskDto;
import com.example.tasksboard.entities.UserTask;
import com.example.tasksboard.models.Role;
import com.example.tasksboard.models.StatusBoard;
import com.example.tasksboard.repositories.UserRepository;
import com.example.tasksboard.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void save(UserTaskDto userTaskDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserTask user = modelMapper.map(userTaskDto, UserTask.class);
        String encode = passwordEncoder.encode(userTaskDto.getPassword());
        user.setPassword(encode);
        userRepository.save(user);
    }

    @Override
    public void update(UserTaskDto userTaskDto) {
        UserTask user = userRepository.findByEmail(getAuthentication().getName());
        if (user.getRole().equals(Role.ADMIN) || user.getId() == userTaskDto.getId()){
            String encode = passwordEncoder.encode(userTaskDto.getPassword());
            UserTask updateUser = userRepository.findById(userTaskDto.getId()).get();
            updateUser.setUserName(userTaskDto.getUserName());
            updateUser.setRole(userTaskDto.getRole());
            updateUser.setStatus(userTaskDto.getStatus());
            updateUser.setPassword(encode);
            updateUser.setEmail(userTaskDto.getEmail());
            userRepository.save(updateUser);
        }
    }

    @Override
    public void deleteById(Long id) {
        UserTask user = userRepository.findByEmail(getAuthentication().getName());

        if (user.getRole().equals(Role.ADMIN) || user.getId() == id) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public void addUserToBoard(UserTaskDto userTaskDto, BoardDto boardDto) {
        if(boardDto.getStatus() == StatusBoard.PUBLIC) {
            List<BoardDto> boards = userTaskDto.getBoardsId();
            userTaskDto.setBoardsId(boards);
            update(userTaskDto);
        }
    }

    @Override
    public void deleteUserFromBoard(UserTaskDto userTaskDto, BoardDto boardDto) {
        List<BoardDto> boards = userTaskDto.getBoardsId();
        boards.remove(boardDto);
        userTaskDto.setBoardsId(boards);
        update(userTaskDto);
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
