package com.example.tasksboard.services.impl;

import com.example.tasksboard.dto.BoardDto;
import com.example.tasksboard.dto.UserTaskDto;
import com.example.tasksboard.entities.Board;
import com.example.tasksboard.entities.Task;
import com.example.tasksboard.entities.UserTask;
import com.example.tasksboard.models.Role;
import com.example.tasksboard.repositories.BoardRepository;
import com.example.tasksboard.repositories.UserRepository;
import com.example.tasksboard.services.BoardService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;


    @Override
    public void createBoard(BoardDto boardDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Board board = modelMapper.map(boardDto, Board.class);
        boardRepository.save(board);
    }

    @Override
    public void deleteBoard(Long id) {
        Board findBoard = boardRepository.findById(id).get();
        UserTask user = userRepository.findByEmail(getAuthentication().getName());
        if (user.getRole().equals(Role.ADMIN) || user.getBoardsId().contains(findBoard.getId())) {
            boardRepository.deleteById(id);
        }
    }

    @Override
    public void updateBoard(BoardDto boardDto) {
        Board getBoard = boardRepository.findById(boardDto.getId()).get();
        getBoard.setName(boardDto.getName());
        getBoard.setStatus(boardDto.getStatus());
        List<Task> boardList = boardDto.getTasksId().stream().map(board -> modelMapper.map(board, Task.class)).collect(Collectors.toList());
        getBoard.setBoardsId(boardList);


        boardRepository.save(getBoard);
    }

    @Override
    public List<BoardDto> getAllBoards() {
        List<Board> boards = boardRepository.findAll();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<BoardDto> boardDtoList = boards.stream().map(board -> modelMapper.map(board, BoardDto.class)).collect(Collectors.toList());
        return boardDtoList;
    }

    @Override
    public void deleteAllBoards() {
        boardRepository.deleteAll();
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
