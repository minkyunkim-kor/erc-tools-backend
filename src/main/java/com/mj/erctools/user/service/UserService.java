package com.mj.erctools.user.service;

import com.mj.erctools.auth.JwtAuthService;
import com.mj.erctools.exception.CustomException;
import com.mj.erctools.exception.ServiceError;
import com.mj.erctools.user.controller.model.UserInfo;
import com.mj.erctools.user.domain.UserEntity;
import com.mj.erctools.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtAuthService jwtAuthService;

    public void saveUser(UserInfo info) throws CustomException {
        UserEntity user = repository.findById(info.getUserId()).orElse(null);

        if (null != user) {
            throw new CustomException(ServiceError.ALREADY_USE_ID);
        }

        user = new UserEntity(info);
        repository.save(user);
    }
}
