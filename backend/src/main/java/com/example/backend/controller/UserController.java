package com.example.backend.controller;

import com.example.backend.dto.ExceptionDTO;
import com.example.backend.dto.UserDTO;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/users/")
public class UserController {

    @Autowired
    public UserService userService;


    @PostMapping("/signup/")
    public ExceptionDTO signUpUser(@RequestBody UserDTO userDTO){
        return userService.signUpUser(userDTO);
    }

    @PostMapping("/login/")
    public ExceptionDTO loginUser(@RequestBody UserDTO userDTO){return userService.loginUser(userDTO);}

}
