package com.mj.erctools.auth.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "erc_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ErcUserEntity {
    @Id
    private String ipAddress;

    private String name;
    private String status;
}
