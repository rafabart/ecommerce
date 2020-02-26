package com.ecommerce.repository;

import com.ecommerce.entity.City;
import com.ecommerce.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    public List<City> findAllByState(State state);
}
