package com.lendops.lendops.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/{name}")
    public ResponseEntity<String> hello(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body("Hello" + name);
    }
}
