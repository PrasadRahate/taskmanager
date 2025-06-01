package com.app.taskmanager.TaskService;

import com.app.taskmanager.Dto.CreateTaskRequest;
import com.app.taskmanager.Dto.TaskDto;
import com.app.taskmanager.Models.Task;
import com.app.taskmanager.Models.User;
import com.app.taskmanager.Repository.TaskRepository;
import com.app.taskmanager.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository ;

    @Autowired
    private UserRepository userRepository ;

    public Task createTask(CreateTaskRequest
             createTaskRequest, String username ) throws Exception{
        User user = userRepository.findByUsername(username).orElseThrow(() ->new UsernameNotFoundException("User not found"));

        Task task = new Task();
        task.setTitle(createTaskRequest.getTitle()) ;
        task.setDescription(createTaskRequest.getDescription());
        task.setStatus(createTaskRequest.getStatus());
        task.setUser(user);

        return taskRepository.save(task);
    }


}
