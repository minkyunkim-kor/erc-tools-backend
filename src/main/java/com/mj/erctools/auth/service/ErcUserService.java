package com.mj.erctools.auth.service;

import com.mj.erctools.auth.model.ErcUserEntity;
import com.mj.erctools.auth.model.ErcUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ErcUserService {

    @Autowired
    private ErcUserRepository repository;

    public HttpStatus checkAuthUser(String ipAddress) {
        ErcUserEntity entity = repository.findById(ipAddress).orElse(null);
        HttpStatus status = HttpStatus.NOT_FOUND;
        if (null != entity) {
            switch(entity.getStatus()) {
                case "ACTIVE":
                    status = HttpStatus.OK;
                    break;
                case "INACTIVE":
                case "PENDING":
                    status = HttpStatus.UNAUTHORIZED;
                    break;
                default:
                    break;
            }
        }
        return status;
    }

    public void registerIpAddress(String ipAddress, String name) {
        ErcUserEntity entity = new ErcUserEntity();
        entity.setIpAddress(ipAddress);
        entity.setName(name);
        entity.setStatus("PENDING");

        repository.save(entity);
    }
}
