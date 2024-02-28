package com.challenge.bci.dto;

import com.challenge.bci.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDTO {
    private String id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modified;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLogin;

    private String token;

    private Boolean isActive;

    public static RegisterResponseDTO entityToDTO(UserEntity userEntity){
        return RegisterResponseDTO.builder()
                .id(userEntity.getUserId())
                .created(userEntity.getCreated())
                .modified(userEntity.getModified())
                .lastLogin(userEntity.getLastLogin())
                .token(userEntity.getToken())
                .isActive(userEntity.getIsActive())
                .build();
    }

}
