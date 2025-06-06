package com.app.taskmanager.Controller;

import com.app.taskmanager.Dto.CreateTaskRequest;
import com.app.taskmanager.Dto.TaskDto;
import com.app.taskmanager.Models.Task;
import com.app.taskmanager.Models.User;
import com.app.taskmanager.Repository.TaskRepository;
import com.app.taskmanager.Repository.UserRepository;
import com.app.taskmanager.TaskService.TaskService;
import com.app.taskmanager.TaskService.TaskServiceImpl;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@Validated
public class TaskController {

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private TaskServiceImpl taskService ;

    @PostMapping("/create")
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody CreateTaskRequest createTaskRequest, Principal principal) throws Exception{
        TaskDto taskResponse = taskService.createTask(createTaskRequest, principal.getName()) ;
        return ResponseEntity.ok(taskResponse) ;

    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTaskByUser(){
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/list")
    public ResponseEntity<Page<TaskDto>> getAllTaskwithPage(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size){
        Page<TaskDto> tasklist = taskService.getAllTasksinPage(page, size);
        return ResponseEntity.ok(tasklist);

    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskByID(@PathVariable long id ){
        return ResponseEntity.ok(taskService.getTaskById(id));
    }


    @PutMapping("update/{id}")
    public ResponseEntity<TaskDto> updateTaskById(@PathVariable long id,@RequestBody TaskDto taskDto){
        return ResponseEntity.ok(taskService.updateTask(id,taskDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDto> deleteTaskById(@PathVariable long id){
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }



}
