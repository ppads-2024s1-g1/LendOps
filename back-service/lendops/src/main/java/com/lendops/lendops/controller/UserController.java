package com.lendops.lendops.controller;

import com.lendops.lendops.dto.UserRequestDTO;
import com.lendops.lendops.orm.User;
import com.lendops.lendops.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/createUser")
    @Transactional
    public ResponseEntity<User> createUser(@RequestBody UserRequestDTO userRequestDTO) {
       User user =  userService.registerUser(userRequestDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> login(@PathVariable String username) {
        User user = userService.findUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/numberOfUsers")
    public ResponseEntity<Long> numberOfUsers() {
        long userCount = userService.getUserCount();
        return new ResponseEntity<>(userCount, HttpStatus.OK);
    }
}