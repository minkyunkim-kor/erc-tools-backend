package com.mj.erctools.user.controller.model;

import com.mj.erctools.user.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserInfo {
    private String userId;
    private String password;
    private String name;
    private String position;
}
