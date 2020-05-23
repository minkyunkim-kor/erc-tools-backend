package com.mj.erctools.map;

import com.mj.erctools.map.domain.LocationInfo;
import com.mj.erctools.map.domain.SearchAddressInfo;
import com.mj.erctools.map.service.FranchiseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class FranchiseController {

    @Autowired
    FranchiseService service;

    @GetMapping("/address")
    public ResponseEntity<SearchAddressInfo> searchAddress(
            @RequestHeader("token") String token,
            @RequestParam("query") String query ) {
        log.info("[Search Address] Token : {}, Query : {}", token, query);
        return ResponseEntity.ok(service.search(token, query));
    }

    @PostMapping("/location")
    public ResponseEntity<Void> saveLocations(
            @RequestHeader("token") String token,
            @RequestBody final LocationInfo info) throws Exception {
        log.info("[Save or Update Location] info : {}", info.toString());
        boolean result = service.saveLocation(token, info);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/location")
    public ResponseEntity<List<LocationInfo>> getAllLocations(@RequestHeader("token") String token) throws Exception {
        List<LocationInfo> results = service.getAllLocationInfo(token);
        if (null == results) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return ResponseEntity.ok(results);
        }
    }

    @DeleteMapping("/location")
    public ResponseEntity<Void> deleteLocation(@RequestHeader("token") String token, @RequestParam("name") String name) {
        log.info("[Delete Location] name : {}", name);
        boolean result = service.deleteLocationInfo(token, name);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
