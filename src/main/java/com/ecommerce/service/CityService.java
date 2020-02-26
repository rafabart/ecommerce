package com.ecommerce.service;

import com.ecommerce.entity.dto.CityDTO;

import java.util.List;

public interface CityService {

    List<CityDTO> findAll(final Long id);
}
