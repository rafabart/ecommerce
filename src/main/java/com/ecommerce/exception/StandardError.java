package com.ecommerce.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardError implements Serializable {

    private Long timeStamp;

    private Integer status;

    private String error;

    private String message;

    private String path;
}
