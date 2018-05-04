package com.inovus.javatest.repositories;

import com.inovus.javatest.model.RegNumber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegNumberRepository extends CrudRepository<RegNumber, Integer> {
}
