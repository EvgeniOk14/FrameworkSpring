package com.example.demo.controllers;

import com.example.demo.model.Task;
import com.example.demo.model.TaskStatus;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;



@RestController
@RequestMapping("/tasks")
public class TaskController
{
    private final TaskRepository taskRepository;
    private final TaskService taskService;

    public TaskController(TaskRepository taskRepository, TaskService taskService)
    {
        this.taskRepository = taskRepository;
        this.taskService=taskService;
    }

    /** создание новой задачи **/
    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody Task task)
    {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.created(URI.create("/tasks/" + createdTask.getId())).body(createdTask);
    }


    /** нахождение всех задач **/
    @GetMapping
    public List<Task> getAllTasks()
    {
        return taskRepository.getAllTasks();
    }


    /** нахождение задачи по её статусу **/
    @GetMapping("/status/{status}")
    public List<Task> getTaskByStatus(@PathVariable TaskStatus status)
    {
        return taskRepository.findByStatus(status);
    }

    /** обновление задачи по её id **/
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable("id") Long id, @RequestBody Task task)
    {
        Task existingTask = taskRepository.findById(id).get();
        if (existingTask != null)
        {
            existingTask.setTaskStatus(task.getTaskStatus());
            return taskRepository.save(existingTask);
        }
        else
        {
            throw new RuntimeException("такой задачи по заданному id  не найдено!");
        }
    }

    /** удаление задачи по её id **/
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") Long id)
        {
            taskRepository.deleteById(id);
        }
}
