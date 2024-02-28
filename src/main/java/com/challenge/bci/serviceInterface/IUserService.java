package com.challenge.bci.serviceInterface;

import com.challenge.bci.dto.RegisterResponseDTO;
import com.challenge.bci.dto.UserDTO;

public interface IUserService {

    RegisterResponseDTO registerUser (UserDTO userDTO);
}
