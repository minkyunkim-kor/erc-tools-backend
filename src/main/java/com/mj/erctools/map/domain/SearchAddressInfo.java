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

    private String size;
    private List<AddressDetail> details;

    public SearchAddressInfo() {
        details = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "SearchAddressInfo{" +
                "size=" + size +
                ", details=" + details +
                '}';
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class AddressDetail {
        private String road;
        private String jibun;
        private Double longitude;   // 경도 (x)
        private Double latitude;    // 위도 (y)

        @Override
        public String toString() {
            return "AddressDetail{" +
                    "road='" + road + '\'' +
                    ", jibun='" + jibun + '\'' +
                    ", longitude=" + longitude +
                    ", latitude=" + latitude +
                    '}';
        }
    }
}
