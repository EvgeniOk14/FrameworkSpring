package com.example.demo.repository;

import com.example.demo.model.Task;
import com.example.demo.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>
{

    @Query("SELECT t FROM Task t WHERE t.taskStatus = :status")
    List<Task> findByStatus(TaskStatus status);
    @Query(value = "SELECT t FROM Task t")
    List<Task> getAllTasks();
}
