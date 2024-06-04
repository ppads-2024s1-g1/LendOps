package com.lendops.lendops.controller;

import com.lendops.lendops.dto.LoginRequestDTO;
import com.lendops.lendops.orm.User;
import com.lendops.lendops.orm.UserAccess;
import com.lendops.lendops.service.UserAccessService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {
    private final UserAccessService userAccessService;

    @PostMapping
    public ResponseEntity<User> loginByUsername(@RequestBody LoginRequestDTO loginRequestDTO) {
        User user = userAccessService.login(loginRequestDTO.username(), loginRequestDTO.password());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /*@GetMapping("/userAccessList")
    public ResponseEntity<List<UserAccess>> getUserAccessList() {
        List<UserAccess> user = userAccessService.getAllUsersAccess();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }*/

    @GetMapping("/accessCount")
    public ResponseEntity<Long> getUserAccessCount() {
        long accessCount = userAccessService.userAccessCount();
        return new ResponseEntity<>(accessCount, HttpStatus.OK);
    }
}
