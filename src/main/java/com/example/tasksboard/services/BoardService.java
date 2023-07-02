package com.example.tasksboard.services;

import com.example.tasksboard.dto.BoardDto;
import com.example.tasksboard.dto.UserTaskDto;

import java.util.List;

public interface BoardService {
    void createBoard(BoardDto boardDto);
    void deleteBoard(Long id);
    void updateBoard(BoardDto boardDto);
    List<BoardDto> getAllBoards();
    void deleteAllBoards();
}
