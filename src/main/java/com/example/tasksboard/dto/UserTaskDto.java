package com.example.tasksboard.dto;


import com.example.tasksboard.models.Role;
import com.example.tasksboard.models.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserTaskDto {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private Role role;
    private Status status;
    private List<BoardDto> boardsId = new ArrayList<BoardDto>();
}
