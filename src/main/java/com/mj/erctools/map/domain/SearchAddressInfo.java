package com.mj.erctools.map.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class SearchAddressInfo {

    private String message;
    private String size;
    private List<AddressDetail> details;

    public SearchAddressInfo() {
        details = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "SearchAddressInfo{" +
                "message='" + message + '\'' +
                ", size='" + size + '\'' +
                ", details=" + details +
                '}';
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class AddressDetail {
        private String address;
        private Double longitude;   // 경도 (x)
        private Double latitude;    // 위도 (y)

        @Override
        public String toString() {
            return "AddressDetail{" +
                    "address='" + address + '\'' +
                    ", longitude=" + longitude +
                    ", latitude=" + latitude +
                    '}';
        }
    }
}
