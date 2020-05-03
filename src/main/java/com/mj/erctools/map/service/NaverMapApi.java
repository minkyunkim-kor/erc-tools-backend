package com.mj.erctools.map.service;

import com.mj.erctools.map.domain.NaverMapAddressInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    value = "NaverMapApiCaller",
    url = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode",
    configuration = NaverMapApiConfiguration.class
)
public interface NaverMapApi {

    @RequestMapping(method = RequestMethod.GET)
    NaverMapAddressInfo getAddressInfo(@RequestParam("query") final String query);
}
