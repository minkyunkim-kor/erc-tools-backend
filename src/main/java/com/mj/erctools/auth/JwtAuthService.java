package com.mj.erctools.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mj.erctools.auth.model.AuthData;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class JwtAuthService {
    private static final String SALT = "STORY_EDU_ERC_TOOLS";
    private static final String SUBJECT = "TOOLS";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public String create(AuthData authData) {
        String jwt = Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setHeaderParam("regDate", LocalDate.now())
                .setSubject(SUBJECT)
                .setClaims(objectMapper.convertValue(authData, Map.class))
                .signWith(SignatureAlgorithm.HS256, SALT.getBytes())
                .compact();
        return jwt;
    }
}
