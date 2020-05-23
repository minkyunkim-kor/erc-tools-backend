package com.mj.erctools.map.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LocationInfo {
    private String name;
    private String teacher;
    private String address;
    private String phonenumber;
    private String status;
    private String details;
    private Double latitude;
    private Double longitude;
}
