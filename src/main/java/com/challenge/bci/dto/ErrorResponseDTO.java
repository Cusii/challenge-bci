package com.challenge.bci.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorResponseDTO {
    private Integer codigo;
    private String mensaje;
    private Date timestamp;
}