package com.mj.erctools.map.service;

import com.mj.erctools.map.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FranchiseService {

    @Autowired
    private FranchiseRepository franchiseRepository;

    @Autowired
    private NaverMapApi naverMapApi;

    public SearchAddressInfo search(String query) {
        NaverMapAddressInfo naverMapAddressInfo = naverMapApi.getAddressInfo(query);
        SearchAddressInfo response = new SearchAddressInfo();

        if (naverMapAddressInfo.getMeta().getTotalCount() > 0) {
            response.setSize(naverMapAddressInfo.getMeta().getTotalCount().toString());
            for (NaverMapAddressInfo.AddressInfo addressInfo : naverMapAddressInfo.getAddresses()) {
                SearchAddressInfo.AddressDetail detail = new SearchAddressInfo.AddressDetail();
                detail.setRoad(addressInfo.getRoadAddress());
                detail.setJibun(addressInfo.getJibunAddress());
                detail.setLongitude(addressInfo.getX());
                detail.setLatitude(addressInfo.getY());

                response.getDetails().add(detail);
            }
        }

        log.info(response.toString());
        return response;
    }

    public void saveOrUpdate(FranchiseInfo info) {
        FranchiseEntity entity = franchiseRepository.findById(info.getName()).orElse(null);
        if (null == entity) {
            entity = new FranchiseEntity();
        }
        entity.setName(info.getName());
        entity.setRoadAddress(info.getRoad());
        entity.setJibunAddress(info.getJibun());
        entity.setPhoneNumber(info.getPhoneNumber());
        entity.setLatitude(info.getLatitude());
        entity.setLongitude(info.getLongitude());
        entity.setStatus("01");
        entity.setMemo(info.getMemo());
        franchiseRepository.save(entity);
    }

    public List<FranchiseInfo> getAllFranchise() {
        List<FranchiseInfo> result = new ArrayList<>();
        Iterable<FranchiseEntity> entities = franchiseRepository.findAll();

        entities.forEach(entity -> {
            result.add(new FranchiseInfo(entity.getName(), entity.getRoadAddress(), entity.getJibunAddress(), entity.getPhoneNumber(), entity.getLatitude(), entity.getLongitude(), entity.getMemo()));
        });

        log.info("[All Franchise Info] Count : {}", result.size());
        return result;
    }

    public void deleteFranchiseInfo(String name) {
        franchiseRepository.deleteById(name);
    }
}
