package com.challenge.bci.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class UserDTO {

    private String name;
    private String email;
    private String password;
    private Set<PhoneDTO> phones;

}
