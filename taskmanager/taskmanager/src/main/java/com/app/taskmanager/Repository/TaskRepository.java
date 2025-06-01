package com.app.taskmanager.Repository;

import com.app.taskmanager.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    public List<Task> findByUserUsername(String username);
}
