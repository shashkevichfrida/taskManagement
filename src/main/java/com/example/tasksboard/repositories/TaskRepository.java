package com.example.tasksboard.repositories;

import com.example.tasksboard.entities.Task;
import com.example.tasksboard.entities.UserTask;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    /*@Query("SELECT task FROM Board task WHERE task.boardsId = :boardId")
    public List<Task> findByBoard(@Param("board") String email);*/


    @Modifying
    @Transactional
    @Query("delete FROM Task task WHERE task.owner.id = :userId")
    public void deleteTasks(@Param("userId") Long userId);
}
