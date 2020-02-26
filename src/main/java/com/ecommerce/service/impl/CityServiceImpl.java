package com.ecommerce.service.impl;

import com.ecommerce.entity.City;
import com.ecommerce.entity.State;
import com.ecommerce.entity.dto.CityDTO;
import com.ecommerce.repository.CityRepository;
import com.ecommerce.service.CityService;
import com.ecommerce.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    final private CityRepository cityRepository;

    final private StateService stateService;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, StateService stateService) {
        this.cityRepository = cityRepository;
        this.stateService = stateService;
    }

    public List<CityDTO> findAll(final Long id) {

        final State state = stateService.findById(id);

        final List<City> cities = cityRepository.findAllByState(state);

        final List<CityDTO> dtoList = cities.stream().map(city -> new CityDTO(city)).collect(Collectors.toList());

        return dtoList;
    }
}
