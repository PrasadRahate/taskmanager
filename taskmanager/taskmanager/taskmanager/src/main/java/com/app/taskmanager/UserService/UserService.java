package com.app.taskmanager.UserService;

import com.app.taskmanager.Dto.UserCreateRequest;
import com.app.taskmanager.Dto.UserDto;
import com.app.taskmanager.Http.UpdateUserRequest;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.data.domain.Page;

public interface UserService {

    public String registerUser(UserCreateRequest userCreateRequest) ;

    public Page<UserDto> getallUsersPaginated(int page,int size);

    public void deleteUserById(long id);

    public Page<UserDto> getAllUsers();

    public UserDto updateUser(UpdateUserRequest userRequest, String username) ;


}
