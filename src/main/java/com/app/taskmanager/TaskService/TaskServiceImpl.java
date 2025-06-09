package com.app.taskmanager.TaskService;

import com.app.taskmanager.Dto.CreateTaskRequest;
import com.app.taskmanager.Dto.TaskDto;
import com.app.taskmanager.Models.Task;
import com.app.taskmanager.Models.User;
import com.app.taskmanager.Repository.TaskRepository;
import com.app.taskmanager.Repository.UserRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class TaskServiceImpl implements TaskService {




    @Autowired
    private TaskRepository taskRepository ;

    @Autowired
    private UserRepository userRepository ;


    private TaskDto taskDto ;

    @Autowired
    private Validator validator ;

    public TaskServiceImpl() {
    }

    private String getLoggedInUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    @Override
    public TaskDto createTask(CreateTaskRequest
                                   createTaskRequest, String username ) {
//        User user = userRepository.findByUsername(username).orElseThrow(() ->new UsernameNotFoundException("User not found"));
//
//        Task task = new Task();
//        task.setTitle(createTaskRequest.getTitle()) ;
//        task.setDescription(createTaskRequest.getDescription());
//        task.setStatus(createTaskRequest.getStatus());
//        task.setUser(user);
//
//
//        taskDto = new TaskDto() ;
//        taskDto.setId(task.getId());
//        taskDto.setDescription(task.getDescription());
//        taskDto.setTitle(task.getTitle());
//        taskRepository.save(task);
//        return taskDto;


        Set<ConstraintViolation<CreateTaskRequest>> violations = validator.validate(createTaskRequest);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Task task = new Task();
        task.setTitle(createTaskRequest.getTitle());
        task.setDescription(createTaskRequest.getDescription());
        task.setStatus(createTaskRequest.getStatus());
        task.setUser(user);

        task = taskRepository.save(task); // Save first to get ID

        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setDescription(task.getDescription());
        taskDto.setTitle(task.getTitle());

        return taskDto;
    }

    @Override
    public Page<TaskDto> getAllTasksinPage(int page, int size) {
        String username = getLoggedInUserEmail();
        Pageable pageable = PageRequest.of(page,size);
        Page<Task> taskpage = taskRepository.findByUserUsername(username,pageable);
        return taskpage.map(this::convertToDTO);
    }


    @Override
    public List<TaskDto> getAllTasks() {
//        String username = getLoggedInUserEmail();
//        List<Task> tasklist = taskRepository.findByUserUsername(username);
//
//        return tasklist.stream().map(task ->{
//            TaskDto taskDto = new TaskDto();
//            taskDto.setId(task.getId());
//            taskDto.setTitle(task.getTitle());
//            taskDto.setDescription(taskDto.getDescription());
//            return taskDto ;
//        }).collect(Collectors.toList());
        return null ;
    }



    private TaskDto convertToDTO(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        // Add other fields as per your DTO
        return dto;
    }

    @Override
    public TaskDto getTaskById(Long id) {
        String username = getLoggedInUserEmail();
         Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));

         if(!task.getUser().getUsername().equals(getLoggedInUserEmail())){
             throw new RuntimeException("Access denied");
         }
         TaskDto taskDto1 = new TaskDto();
         taskDto1.setId(task.getId());
         taskDto1.setTitle(task.getTitle());
         taskDto1.setDescription(task.getDescription());
         return taskDto1 ;
    }

    @Override
    public TaskDto updateTask(Long id, TaskDto dto) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        if(!task.getUser().getUsername().equals(getLoggedInUserEmail())){
            throw new RuntimeException("Access denied") ;
        }

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        taskRepository.save(task);
        dto.setId(task.getId());
        return dto ;
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("task not found!!"));

        if(!task.getUser().getUsername().equals(getLoggedInUserEmail())){
            throw new RuntimeException("Access denied") ;
        }
        taskRepository.delete(task);
    }
}
