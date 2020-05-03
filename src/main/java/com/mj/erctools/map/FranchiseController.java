package com.mj.erctools.map;

import com.mj.erctools.map.domain.FranchiseInfo;
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
    public ResponseEntity<SearchAddressInfo> searchAddress( @RequestParam("query") String query ) {
        log.info("[Search Address] Query : {}", query);
        return ResponseEntity.ok(service.search(query));
    }

    @PostMapping("/franchises")
    public ResponseEntity<Void> saveFranchise(@RequestBody final FranchiseInfo info) {
        log.info("[Save or Update Franchise] info : {}", info.toString());
        service.saveOrUpdate(info);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/franchises")
    public ResponseEntity<List<FranchiseInfo>> getAllFranchises() {
        return ResponseEntity.ok(service.getAllFranchise());
    }

    @DeleteMapping("/franchises")
    public ResponseEntity<Void> deleteFranchise(@RequestParam("name") String name) {
        log.info("[Delete Franchise] name : {}", name);
        service.deleteFranchiseInfo(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
