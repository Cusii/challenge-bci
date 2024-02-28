package com.challenge.bci.entity;

import com.challenge.bci.dto.PhoneDTO;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "phone")
public class PhoneEntity {

    @Id
    @Column(name = "number")
    private Long number;

    @Column(name = "city_code")
    private Integer cityCode;

    @Column(name = "country_code")
    private String countryCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public static PhoneEntity fromDtoToEntity(PhoneDTO phoneDTO) {
        return PhoneEntity.builder()
                .number(phoneDTO.getNumber())
                .cityCode(phoneDTO.getCityCode())
                .countryCode(phoneDTO.getCountryCode())
                .build();
    }
}
