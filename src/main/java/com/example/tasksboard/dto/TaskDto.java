package com.example.tasksboard.dto;

import com.example.tasksboard.entities.Board;
import com.example.tasksboard.entities.UserTask;
import com.example.tasksboard.models.StateTask;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskDto {
    private Long id;
    private String text;
    private UserTaskDto owner;
    private StateTask stateTask;
    private Date dateOfCreation;
    private BoardDto boardId;
}
