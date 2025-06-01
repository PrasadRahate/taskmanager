package com.app.taskmanager.Controller;

import com.app.taskmanager.Dto.CreateTaskRequest;
import com.app.taskmanager.Dto.TaskDto;
import com.app.taskmanager.Models.Task;
import com.app.taskmanager.Models.User;
import com.app.taskmanager.Repository.TaskRepository;
import com.app.taskmanager.Repository.UserRepository;
import com.app.taskmanager.TaskService.TaskService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private TaskService taskService ;

    @PostMapping("/create")
    public ResponseEntity<TaskDto> createTask(@RequestBody CreateTaskRequest createTaskRequest, Principal principal) throws Exception{
        Task task = taskService.createTask(createTaskRequest, principal.getName()) ;

        TaskDto taskResponse = new TaskDto(task.getId(),task.getTitle(),task.getDescription());
        return ResponseEntity.ok(taskResponse) ;

    }



}
