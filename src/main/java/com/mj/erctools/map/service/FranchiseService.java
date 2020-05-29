package com.mj.erctools.map.service;

import com.mj.erctools.map.domain.*;
import com.mj.erctools.users.model.UserEntity;
import com.mj.erctools.users.model.UserRepository;
import com.mj.erctools.util.AES;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FranchiseService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private NaverMapApi naverMapApi;

    @Autowired
    private UserRepository userRepository;

    public SearchAddressInfo search(String token, String query) {
        UserEntity user = userRepository.findByToken(token);
        if (null == user) {
            return new SearchAddressInfo("유효하지 않은 접근입니다.", "0", new ArrayList<>());
        }

        NaverMapAddressInfo naverMapAddressInfo = naverMapApi.getAddressInfo(query);
        SearchAddressInfo response = new SearchAddressInfo();

        if (naverMapAddressInfo.getMeta().getTotalCount() > 0) {
            response.setSize(naverMapAddressInfo.getMeta().getTotalCount().toString());
            for (NaverMapAddressInfo.AddressInfo addressInfo : naverMapAddressInfo.getAddresses()) {
                SearchAddressInfo.AddressDetail detail = new SearchAddressInfo.AddressDetail();
                detail.setAddress(convertAddress(addressInfo.getAddressElements()));
                detail.setLongitude(addressInfo.getX());
                detail.setLatitude(addressInfo.getY());

                response.getDetails().add(detail);
            }
        }

        log.info(response.toString());
        return response;
    }

    public boolean saveLocation(String token, LocationInfo location) throws Exception {
        UserEntity user = userRepository.findByToken(token);
        if (null == user) {
            return false;
        }

        LocationEntity entity = new LocationEntity(location);

        AES aes = new AES();

        entity.setTeacher(aes.encrypt(entity.getTeacher()));
        entity.setPhonenumber(aes.encrypt(entity.getPhonenumber()));

        locationRepository.save(entity);
        return true;
    }

    public List<LocationInfo> getAllLocationInfo(String token) throws Exception {
        UserEntity user = userRepository.findByToken(token);
        if (null == user) {
            return null;
        }

        List<LocationInfo> result = new ArrayList<>();
        Iterable<LocationEntity> entities = locationRepository.findAll();

        AES aes = new AES();

        for (LocationEntity entity : entities) {
            LocationInfo info = new LocationInfo();
            info.setName(entity.getName());
            info.setTeacher(aes.decrypt(entity.getTeacher()));
            info.setAddress(entity.getAddress());
            info.setPhonenumber(aes.decrypt(entity.getPhonenumber()));
            info.setStatus(entity.getStatus());
            info.setDetails(entity.getDetails());
            info.setLatitude(entity.getLatitude());
            info.setLongitude(entity.getLongitude());

            result.add(info);
        }

        return result;
    }

    public boolean deleteLocationInfo(String token, String name) {
        UserEntity user = userRepository.findByToken(token);
        if (null == user) {
            return false;
        }
        for(LocationEntity locationEntity : locationRepository.findAll()) {
            if (locationEntity.getName().equals(name)) {
                locationRepository.delete(locationEntity);
                return true;
            }
        }
        return false;
    }

    private String convertAddress(List<NaverMapAddressInfo.AddressElement> elements) {
        String address = "SIDO SIGUGUN DONGMYUN RI LAND_NUMBER (ROAD_NAME BUILDING_NUMBER) BUILDING_NAME";

        for (NaverMapAddressInfo.AddressElement element : elements) {
            if (!"".equals(element.getLongName())) {
                address = address.replace(element.getTypes().get(0), element.getLongName());
            } else {
                address = address.replace(element.getTypes().get(0) + " ", "");
            }
        }

        return address;
    }
}
