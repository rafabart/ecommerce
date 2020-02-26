package com.ecommerce.service;

import com.ecommerce.entity.State;
import com.ecommerce.entity.dto.StateDTO;

import java.util.List;

public interface StateService {

    List<StateDTO> findAll();

    State findById(final Long id);
}
