package com.mj.erctools.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/users/test")
    public ResponseEntity<String> testApi() {
        return ResponseEntity.ok("Hello, World");
    }
}
