package com.example.tasksboard.repositories;

import com.example.tasksboard.entities.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserTask, Long> {
    @Query("SELECT DISTINCT user FROM UserTask user WHERE user.email = :email")
    public UserTask findByEmail(@Param("email") String email);
}
