package com.ecommerce.entity.dto;

import com.ecommerce.entity.State;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class StateDTO implements Serializable {

    private Long id;

    private String name;

    public StateDTO(final State state) {
        this.id = state.getId();
        this.name = state.getName();
    }
}
