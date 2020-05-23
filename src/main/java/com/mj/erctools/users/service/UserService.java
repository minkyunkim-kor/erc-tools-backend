package com.mj.erctools.users.service;

import com.mj.erctools.users.domain.EnrollUser;
import com.mj.erctools.users.domain.Login;
import com.mj.erctools.users.model.UserEntity;
import com.mj.erctools.users.model.UserRepository;
import com.mj.erctools.users.model.UserVO;
import com.mj.erctools.util.AES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;


    public boolean checkUserId(String id) {
        UserEntity user = repository.findById(id).orElse(null);

        return user == null;
    }

    public EnrollUser enrollUser(UserVO request) throws Exception {
        UserEntity user = repository.findById(request.getId()).orElse(null);
        if (null != user) {
            return new EnrollUser(false, "이미 가입된 ID 입니다.");
        }

        AES aes = new AES();


        UserEntity registerTarget = new UserEntity(request.getId(), aes.encrypt(request.getPw()), aes.encrypt(request.getName()));
        repository.save(registerTarget);

        return new EnrollUser(true, null);
    }

    public Login login(UserVO request) throws Exception {
        UserEntity user = repository.findById(request.getId()).orElse(null);
        if (null == user) {
            return new Login(false, null, "가입되지 않은 ID 입니다.");
        }

        AES aes = new AES();

        String savedPw = aes.decrypt(user.getPassword());

        if (!request.getPw().equals(savedPw)) {
            return new Login(false, null, "비밀번호가 틀렸습니다.");
        }

        String token = UUID.randomUUID().toString();
        LocalDateTime expiredTime = LocalDateTime.now().plusHours(8);

        user.setToken(token);
        user.setTokenExpired(expiredTime);

        repository.save(user);

        return new Login(true, token, null);
    }
}
