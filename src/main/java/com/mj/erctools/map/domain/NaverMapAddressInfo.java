package com.mj.erctools.map.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NaverMapAddressInfo {
    private String status;
    private MetaInfo meta;
    private List<AddressInfo> addresses;
    private String errorMessage;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class MetaInfo {
        private Integer totalCount;
        private Integer page;
        private Integer count;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class AddressInfo {
        private List<AddressElement> addressElements;
        private Double x;
        private Double y;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class AddressElement {
        private List<String> types;
        private String longName;
    }
}
