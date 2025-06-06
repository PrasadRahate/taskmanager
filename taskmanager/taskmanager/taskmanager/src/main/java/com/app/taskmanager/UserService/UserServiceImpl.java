package com.app.taskmanager.UserService;

import com.app.taskmanager.Dto.TaskDto;
import com.app.taskmanager.Dto.UserCreateRequest;
import com.app.taskmanager.Dto.UserDto;
import com.app.taskmanager.Http.UpdateUserRequest;
import com.app.taskmanager.Models.Role;
import com.app.taskmanager.Models.Task;
import com.app.taskmanager.Models.User;
import com.app.taskmanager.Repository.UserRepository;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private PasswordEncoder encoder ;

    private String getLoggedInUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    @Override
    public String registerUser(UserCreateRequest userCreateRequest) {
        if(userRepository.existsByUsername(userCreateRequest.getUsername()) && userRepository.existsByEmail(userCreateRequest.getEmail())){
            throw new RuntimeException("User already exist with this email/username");
        }
        User user = new User() ;
        user.setUsername(userCreateRequest.getUsername());
        user.setEmail(userCreateRequest.getEmail());
        user.setRole(Role.USER);
        user.setPassword(encoder.encode(userCreateRequest.getPassword()));
        userRepository.save(user);
        return "User registered successfully!!";

    }

    @Override
    public Page<UserDto> getallUsersPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(this::convertToDTO);

    }
    private UserDto convertToDTO(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        // Add other fields as per your DTO
        return dto;
    }

    @Override
    public void deleteUserById( long id) {
        User user= userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        userRepository.delete(user);

    }

    @Override
    public Page<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public UserDto updateUser(UpdateUserRequest usereq , String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setUsername(usereq.getUsername());
        user.setEmail(usereq.getEmail());
        userRepository.save(user);

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());

        return dto;
    }
}
