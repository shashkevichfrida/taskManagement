package com.example.tasksboard.entities;

import com.example.tasksboard.models.StatusBoard;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Board")
@Getter
@Setter
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "taskId")
    @JsonIgnore
    private List<Task> boardsId = new ArrayList<Task>();

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private StatusBoard status;
}
