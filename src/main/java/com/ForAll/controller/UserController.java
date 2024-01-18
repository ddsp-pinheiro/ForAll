package com.ForAll.controller;

import com.ForAll.model.UserModel;
import com.ForAll.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {



    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserModel>> getAll(){
        return ResponseEntity.ok(this.userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getById(@PathVariable Long id){
        return this.userService.getById(id);

    }

    @GetMapping("name/{name}")
    public ResponseEntity<List<UserModel>> getAllByName(@PathVariable String name) {
        return this.userService.getAllByName(name);
    }

    @GetMapping("email/{email}")
    public ResponseEntity<List<UserModel>> getAllByEmail(@PathVariable String email){
        return this.userService.getAllByEmail(email);
    }

    @PostMapping
    public ResponseEntity<UserModel> postUser(@RequestBody UserModel user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(user));
    }

    @PutMapping
    public ResponseEntity<UserModel> UpdateUser(@RequestBody UserModel user) {
        return this.userService.UpdateUser(user);
    }

}
