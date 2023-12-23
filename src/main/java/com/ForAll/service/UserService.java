package com.ForAll.service;

import com.ForAll.model.UserModel;
import com.ForAll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

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
        return userRepository.getReferenceById(id);
    }

    public Optional<UserModel> getByName(String name){
        return userRepository.findByName(name);
    }
}
