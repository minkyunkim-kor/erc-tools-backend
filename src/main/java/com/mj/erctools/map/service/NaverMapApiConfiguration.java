package com.mj.erctools.map.service;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NaverMapApiConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate ->
                requestTemplate.header("X-NCP-APIGW-API-KEY-ID", "j79ciqblp1")
                        .header("X-NCP-APIGW-API-KEY", "RHuR2GaEXQjxfcjNwbk9UC9ZrVFftufzv0MihNwH");
    }
}
