package com.realtime.chat.controller;

import com.realtime.chat.dtos.SubscribeDTO;
import com.realtime.chat.dtos.UserDTO;
import com.realtime.chat.entities.User;
import com.realtime.chat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserDTO userDTO) {

        User newUser = new User(userDTO.username(), userDTO.password());

        userRepository.save(newUser);

        return ResponseEntity.ok(newUser);
    }

}
