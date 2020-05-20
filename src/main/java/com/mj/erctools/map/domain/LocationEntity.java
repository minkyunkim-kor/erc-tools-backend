package com.mj.erctools.map.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "location")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LocationEntity {
    @Id
    private String name;
    private String teacher;
    private String address;
    private String phonenumber;
    private String status;
    private String details;
    private Long latitude;
    private Long longitude;

    public LocationEntity(LocationInfo locationInfo) {
        name = locationInfo.getName();
        teacher = locationInfo.getTeacher();
        address = locationInfo.getAddress();
        phonenumber = locationInfo.getPhonenumber();
        status = locationInfo.getStatus();
        details = locationInfo.getDetails();
        latitude = locationInfo.getLatitude();
        longitude = locationInfo.getLongitude();
    }
}