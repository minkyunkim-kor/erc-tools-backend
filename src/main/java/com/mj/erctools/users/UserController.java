package com.mj.erctools.users;

import com.mj.erctools.users.domain.EnrollUser;
import com.mj.erctools.users.domain.Login;
import com.mj.erctools.users.model.UserVO;
import com.mj.erctools.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<EnrollUser> enrollUser(@RequestBody UserVO request) throws Exception {
        return ResponseEntity.ok(service.enrollUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<Login> login(@RequestBody UserVO request) throws Exception {
        return ResponseEntity.ok(service.login(request));
    }
}
