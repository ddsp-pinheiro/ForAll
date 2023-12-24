package com.ForAll.service;

import com.ForAll.exception.NotFoundException;
import com.ForAll.model.UserModel;
import com.ForAll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        UserModel user = getById(id);
        userRepository.delete(user);
    }

    public UserModel getById(Long id){
        return userRepository.findById(id).
                orElseThrow(() -> new NotFoundException(IdNotFound));
    }

    public List<UserModel> getAll(){
        return userRepository.findAll();
    }

    public Optional<UserModel> getByName(String name){
        return userRepository.findByName(name);
    }
}
