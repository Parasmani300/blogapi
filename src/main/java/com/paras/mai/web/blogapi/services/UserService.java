package com.paras.mai.web.blogapi.services;

import com.paras.mai.web.blogapi.payloads.UserDto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto user,Integer id);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
    List<UserDto> getUserOrderedByEmail();

}
