package com.paras.mai.web.blogapi.controller;

import com.paras.mai.web.blogapi.payloads.ApiRsponse;
import com.paras.mai.web.blogapi.payloads.UserDto;
import com.paras.mai.web.blogapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RestTemplate restTemplate;

//    GET
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        List<UserDto> userDtos = this.userService.getAllUsers();

//        RandomDto randomDto = restTemplate.getForObject("https://random-data-api.com/api/app/random_app",RandomDto.class);
//        System.out.println(String.valueOf(randomDto));

        return  new ResponseEntity<>(userDtos,HttpStatus.OK);
    }

    @GetMapping("/chk1")
    public ResponseEntity<List<UserDto>> getAllNativeQuery()
    {
        List<UserDto> userDtos = this.userService.getUserOrderedByEmail();

        List<UserDto> filteredList = userDtos.stream()
                .map((usr) -> {
                    usr.setPassword("1234");
                    return usr;
                })
                .filter((usr)->usr.getName().equals("Mani"))
                .collect(Collectors.toList());

        Map<Integer, String> myCollection = userDtos
                .stream()
                .collect(Collectors.toMap(UserDto::getId,UserDto::getName));
        myCollection.forEach((k,v)->{
            System.out.println(k + " : " + v);
        });

//        RandomDto randomDto = restTemplate.getForObject("https://random-data-api.com/api/app/random_app",RandomDto.class);
//        System.out.println(String.valueOf(randomDto));

        return  new ResponseEntity<>(filteredList,HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Integer userId)
    {
        try {
            UserDto userDto = this.userService.getUserById(userId);
            return  new ResponseEntity<>(userDto,HttpStatus.OK);
        }catch (Exception ex){
            return  new ResponseEntity<>(new ApiRsponse(ex.getLocalizedMessage(),false),HttpStatus.NOT_FOUND);
        }

    }

//    POST
    @RequestMapping(path = "/",method = RequestMethod.POST)
    public ResponseEntity<UserDto> postUser(@Valid @RequestBody UserDto userDto)
    {
        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

//    PUT
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer userId)
    {
        UserDto userDto1 = this.userService.updateUser(userDto,userId);
        return ResponseEntity.ok(userDto1);
    }

//    DELETE
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiRsponse> deleteUser(@PathVariable("userId") Integer userId)
    {
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiRsponse("User deleted successfully",true),HttpStatus.OK);
    }
}
