package com.ForAll.service;

import com.ForAll.exception.NotFoundException;
import com.ForAll.model.UserModel;
import com.ForAll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final String IdNotFound = "ID Not Found";
    private static final String NameNotFound = "Name Not Found";

    @Autowired
    private UserRepository userRepository;

    public UserModel addUser(UserModel user){
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        UserModel user = getById(id).getBody();
        userRepository.delete(user);
    }

    // Metodo Mostrar por ID
    public ResponseEntity<UserModel> getById(Long id) {
        return userRepository.findById(id).map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        }
    // Metodo Mostrar todos
    public List<UserModel> getAll(){
        return userRepository.findAll();
    }

    // Metodo Mostrar por nome
    public ResponseEntity<List<UserModel>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(userRepository.findByNameContainingIgnoreCase(name));
    }
}
