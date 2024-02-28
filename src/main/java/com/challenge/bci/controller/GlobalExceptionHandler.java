package com.challenge.bci.controller;

import com.challenge.bci.dto.ErrorResponseDTO;
import com.challenge.bci.exception.RequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<ErrorResponseDTO> handleRequestException(RequestException ex) {
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                ex.getCodigo(),
                ex.getMessage(),
                new Date()
        );
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}