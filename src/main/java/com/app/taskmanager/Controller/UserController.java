package com.app.taskmanager.Controller;


import com.app.taskmanager.Dto.UserCreateRequest;
import com.app.taskmanager.Dto.UserDto;
import com.app.taskmanager.Http.UpdateUserRequest;
import com.app.taskmanager.Models.User;
import com.app.taskmanager.Repository.UserRepository;
import com.app.taskmanager.UserService.UserServiceImpl;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserServiceImpl  userService ;


    @PostMapping("/new")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserCreateRequest userCreateRequest){

        String mess = userService.registerUser(userCreateRequest) ;
        return ResponseEntity.ok(mess);
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserDto>> findAllUser(@RequestParam(defaultValue = "0")int page, @RequestParam (defaultValue = "10")int size){
        Page<UserDto> userDto = userService.getallUsersPaginated(page,size);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUserById(@PathVariable long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok("User deleted successfully");
    }


    @PutMapping("/update")
    public ResponseEntity<UserDto> selfupdate(@Valid @RequestBody UpdateUserRequest req){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto updatedUser = userService.updateUser(req, username);
        return ResponseEntity.ok(updatedUser);
    }

}
