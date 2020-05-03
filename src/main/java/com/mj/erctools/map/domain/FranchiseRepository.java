package com.mj.erctools.map.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseRepository extends CrudRepository<FranchiseEntity, String> {
}
