package com.ecommerce.resource;

import com.ecommerce.entity.State;
import com.ecommerce.entity.dto.CityDTO;
import com.ecommerce.entity.dto.StateDTO;
import com.ecommerce.service.CityService;
import com.ecommerce.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/states")
public class StateResource {

    final private StateService stateService;

    final private CityService cityService;


    @Autowired
    public StateResource(final StateService stateService, final CityService cityService) {
        this.stateService = stateService;
        this.cityService = cityService;
    }


    @GetMapping
    public ResponseEntity<List<StateDTO>> findAll() {

        final List<StateDTO> states = stateService.findAll();

        return ResponseEntity.ok(states);
    }


    @GetMapping("/{idState}/cities")
    public ResponseEntity<List<CityDTO>> findAllByState(@PathVariable("idState") final Long id) {

        final List<CityDTO> cities = cityService.findAll(id);

        return ResponseEntity.ok(cities);
    }
}
