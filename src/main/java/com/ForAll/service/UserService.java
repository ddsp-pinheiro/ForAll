package com.ForAll.service;

import com.ForAll.model.UserModel;
import com.ForAll.repository.UserRepository;
import com.ForAll.util.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailSender mailSender;


    public UserModel addUser(UserModel user) {
        try {
            userRepository.save(user);

        } catch (Exception e) {
            //TODO: include Exception msg
        }
        sendWellComeMail(user.getEmail());
        return user;
    }

    private void sendWellComeMail(String email)  {
        mailSender.sendMail(email,
                "Welcome to For All",
                "src/main/java/com/ForAll/util/templates/WelcomeTemplate.html");
    }


    public void deleteUser(Long id) {
        UserModel user = getById(id).getBody();
        userRepository.delete(user);
    }

    public ResponseEntity<UserModel> getById(Long id) {
        return userRepository.findById(id).map(ResponseEntity::ok)
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
