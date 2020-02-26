package com.ecommerce.entity.dto;

import com.ecommerce.entity.City;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CityDTO implements Serializable {

    private Long id;

    private String name;

    public CityDTO(final City city) {
        this.id = city.getId();
        this.name = city.getName();
    }
}
