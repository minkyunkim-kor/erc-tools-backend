package com.mj.erctools.user.domain;

import com.mj.erctools.user.controller.model.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "ERC_USER")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    private String USER_ID;
    private String USER_PW;
    private String NAME;
    private String POSITION;
    private String STATUS;

    public UserEntity(UserInfo info) {
        USER_ID = info.getUserId();
        USER_PW = info.getPassword();
        NAME = info.getName();
        POSITION = info.getPosition();
        STATUS = "PENDING";
    }
}
