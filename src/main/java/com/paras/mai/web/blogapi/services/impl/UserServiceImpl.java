package com.paras.mai.web.blogapi.services.impl;

import com.paras.mai.web.blogapi.dao.UserRepo;
import com.paras.mai.web.blogapi.entities.User;
import com.paras.mai.web.blogapi.exception.ResourceNotFoundException;
import com.paras.mai.web.blogapi.payloads.UserDto;
import com.paras.mai.web.blogapi.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto user, Integer id) {
        User user1 = this.userRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("user","id",id));

        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setAbout(user.getAbout());

        User updateUser = this.userRepo.save(user1);
        return userToDto(updateUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user1 = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("user","id",1L));

        return userToDto(user1);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();

        List<UserDto> userDtos = users.stream().map(user->userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user1 = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("user","id",userId));

        this.userRepo.delete(user1);
    }

    @Override
    public List<UserDto> getUserOrderedByEmail() {
        List<User> users = this.userRepo.findUserOrderByNameAndEmail().orElseThrow(()-> new ResourceNotFoundException("user","id",1L));

        List<UserDto> theUsers = users.stream().map((usr)->userToDto(usr)).collect(Collectors.toList());
        return  theUsers;
    }

    public User dtoToUser(UserDto userDto)
    {
//        User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());

        User user = this.modelMapper.map(userDto,User.class);

        return user;
    }

    public  UserDto userToDto(User user)
    {
        UserDto userDto = this.modelMapper.map(user,UserDto.class);
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());

        return userDto;
    }
}
