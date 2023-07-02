package com.example.tasksboard.dto;


import com.example.tasksboard.models.StatusBoard;
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
public class BoardDto {
    private Long id;
    private String name;
    private List<TaskDto> tasksId = new ArrayList<TaskDto>();
    private StatusBoard status;
}
