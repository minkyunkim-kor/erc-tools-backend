package com.mj.erctools.map.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "franchise")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FranchiseEntity {
    @Id
    private String name;
    private String roadAddress;
    private String jibunAddress;
    private String phoneNumber;
    private String status;
    private Double latitude;
    private Double longitude;
    private String memo;
}
