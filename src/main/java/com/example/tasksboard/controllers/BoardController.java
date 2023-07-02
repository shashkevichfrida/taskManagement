package com.example.tasksboard.controllers;

import com.example.tasksboard.dto.BoardDto;
import com.example.tasksboard.services.impl.BoardServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@Tag(name = "board", description = "board APIs")
@PreAuthorize("hasAnyAuthority('developers:read', 'developers:write')")
@AllArgsConstructor
public class BoardController {

    private final BoardServiceImpl boardService;

    @PostMapping("/createBoard")
    public void createBoard(@RequestBody BoardDto boardDto){
        boardService.createBoard(boardDto);
    }

    @DeleteMapping("/deleteBoard")
    public void deleteBoard(@RequestBody Long id){
        boardService.deleteBoard(id);
    }


    @PostMapping("/updateBoard")
    public void updateBoard(@RequestBody BoardDto boardDto){
        boardService.updateBoard(boardDto);
    }

    @PostMapping("/getAllBoards")
    public List<BoardDto> getAllBoards(){
        return boardService.getAllBoards();
    }

    @DeleteMapping("/deleteAllBoards")
    public void deleteAllBoards(){
        boardService.deleteAllBoards();
    }
}
