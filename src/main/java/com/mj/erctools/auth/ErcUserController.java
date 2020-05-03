package com.mj.erctools.auth;

import com.mj.erctools.auth.model.RegisterUserInfo;
import com.mj.erctools.auth.service.ErcUserService;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class ErcUserController {

    @Autowired
    private ErcUserService service;

    @GetMapping("/users/check")
    public ResponseEntity<Void> checkAuthUser(@RequestParam("ip") final String ipAddress) {
        log.info("[Check IP Address] IP : {}", ipAddress);
        HttpStatus status = service.checkAuthUser(ipAddress);

        return new ResponseEntity<>(status);
    }

    @PostMapping("/users")
    public ResponseEntity<Void> registerIp(@RequestBody RegisterUserInfo info) {
        log.info("[Register IP Address] IP : {}, name : {}", info.getIp(), info.getName());
        service.registerIpAddress(info.getIp(), info.getName());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
