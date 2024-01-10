package com.ForAll.service;

import com.ForAll.exception.NotFoundException;
import com.ForAll.model.UserModel;
import com.ForAll.repository.UserRepository;
import org.apache.catalina.User;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final String IdNotFound = "ID Not Found";
    private static final String NameNotFound = "Name Not Found";

    @Autowired
    private UserRepository userRepository;

    public UserModel addUser(UserModel user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        UserModel user = getById(id).getBody();
        userRepository.delete(user);
    }

    public ResponseEntity<UserModel> getById(Long id) {
        return userRepository.findById(id).map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public List<UserModel> getAll() {
        return userRepository.findAll();
    }

    public ResponseEntity<List<UserModel>> getAllByName(@PathVariable String name) {

        List<UserModel> users = userRepository.findByNameContainingIgnoreCase(name);
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(users);
        }
    }

    public ResponseEntity<UserModel> UpdateUser(UserModel user) {
        return userRepository.findById(user.getId())
                .map(response -> ResponseEntity.ok(userRepository.save(user)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
