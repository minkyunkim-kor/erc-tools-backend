package com.mj.erctools.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthData implements Serializable {
    private String id;
    private LocalDateTime expiredDateTime;
}
