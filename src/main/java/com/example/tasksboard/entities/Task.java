package com.example.tasksboard.entities;


import com.example.tasksboard.models.StateTask;
import com.example.tasksboard.models.StatusBoard;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Task")
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @OneToOne(mappedBy = "task", cascade = CascadeType.MERGE)
    private UserTask owner;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "state")
    private StateTask stateTask;

    @Column(name = "dateOfCreation")
    private Date dateOfCreation;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "BoardId")
    @JsonIgnore
    private Board boardId = new Board();

}
