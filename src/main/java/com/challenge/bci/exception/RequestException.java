package com.challenge.bci.exception;

import lombok.Data;

import java.util.Date;

@Data
public class RequestException extends RuntimeException{
    private Integer codigo;
    private Date timestamp;
    public RequestException (Date timestamp, Integer code, String detail){
        super(detail);
        this.timestamp = timestamp;
        this.codigo = code;

    }
}
