package com.mj.erctools.map.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FranchiseInfo {
    private String name;
    private String road;
    private String jibun;
    private String phoneNumber;
    private Double latitude;
    private Double longitude;
    private String memo;
}
