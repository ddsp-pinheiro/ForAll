package com.ForAll.controller;

import com.ForAll.model.UserModel;
import com.ForAll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping()
    public ResponseEntity<UserModel> postUser(@RequestBody UserModel user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(user));
    }

}
