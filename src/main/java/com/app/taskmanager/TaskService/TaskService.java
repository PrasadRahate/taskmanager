package com.app.taskmanager.TaskService;

import com.app.taskmanager.Dto.CreateTaskRequest;
import com.app.taskmanager.Dto.TaskDto;
import com.app.taskmanager.Models.Task;
import com.app.taskmanager.Models.User;
import com.app.taskmanager.Repository.TaskRepository;
import com.app.taskmanager.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
        TaskDto createTask(CreateTaskRequest
                                   createTaskRequest, String username );
        Page<TaskDto> getAllTasksinPage(int page, int size);

        List<TaskDto> getAllTasks() ;
        TaskDto getTaskById(Long id);
        TaskDto updateTask(Long id, TaskDto dto);
        void deleteTask(Long id);
    }

