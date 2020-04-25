package com.mj.erctools.user.controller;

import com.mj.erctools.exception.CustomException;
import com.mj.erctools.user.controller.model.UserInfo;
import com.mj.erctools.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/users")
    public ResponseEntity<Void> registUser(@RequestBody final UserInfo info ) throws CustomException {
        userService.saveUser(info);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
