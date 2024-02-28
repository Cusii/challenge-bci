package com.challenge.bci.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PhoneDTO {
    private Long number;
    private Integer cityCode;
    private String countryCode;
}
