package com.ecommerce.service.impl;

import com.ecommerce.entity.State;
import com.ecommerce.entity.dto.StateDTO;
import com.ecommerce.exception.ObjectNotFoundException;
import com.ecommerce.repository.StateRepository;
import com.ecommerce.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StateServiceImpl implements StateService {

    final private StateRepository stateRepository;


    @Autowired
    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }


    public List<StateDTO> findAll() {

        final List<State> states = stateRepository.findAllByOrderByName();

        final List<StateDTO> dtoList = states.stream().map(state -> new StateDTO(state)).collect(Collectors.toList());

        return dtoList;
    }


    public State findById(final Long id) {

        final State state = stateRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Estado")
        );

        return state;
    }
}
