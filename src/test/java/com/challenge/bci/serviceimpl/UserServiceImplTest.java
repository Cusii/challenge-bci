package com.challenge.bci.serviceimpl;

import com.challenge.bci.dto.PhoneDTO;
import com.challenge.bci.dto.RegisterResponseDTO;
import com.challenge.bci.dto.UserDTO;
import com.challenge.bci.entity.UserEntity;
import com.challenge.bci.exception.RequestException;
import com.challenge.bci.repository.UserRepository;
import com.challenge.bci.serviceImpl.UserServiceImpl;
import com.challenge.bci.utils.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_Test(){
        UserDTO userDTO = createUserDTO();
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any())).thenReturn(createUserEntity());

        RegisterResponseDTO responseDTO = userService.registerUser(userDTO);
        assertNotNull(responseDTO);
        assertNotNull(responseDTO.getId());
        assertTrue(responseDTO.getIsActive());

    }
    @Test
    void registerUser_ExisteEmailTest() {
        UserDTO userDTO = createUserDTO();
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.of(createUserEntity()));
        assertThrows(RequestException.class, () -> userService.registerUser(userDTO));
    }

    @Test
    void registerUser_InvalidEmailTest() {
        UserDTO userDTO = createUserDTO();
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.of(createUserEntity()));
        assertThrows(RequestException.class, () -> userService.registerUser(userDTO));
    }

    private UserDTO createUserDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("test");
        userDTO.setEmail("test.test@example.com");
        userDTO.setPassword("password");
        userDTO.setPhones(createPhoneDTO());
        return userDTO;
    }

    private Set<PhoneDTO> createPhoneDTO() {
        PhoneDTO phoneDTO = new PhoneDTO();
        phoneDTO.setNumber(123456789L);
        phoneDTO.setCityCode(54);
        phoneDTO.setCountryCode("ARG");

        Set<PhoneDTO> phoneDTOSet = new HashSet<>();
        phoneDTOSet.add(phoneDTO);
        return phoneDTOSet;
    }

    private UserEntity createUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId("ac4628b5-18d7-4646-8e82-1b3d43eb1523");
        userEntity.setName("test");
        userEntity.setEmail("test@test.com");
        userEntity.setPassword("test");
        userEntity.setCreated(new Date());
        userEntity.setModified(new Date());
        userEntity.setLastLogin(new Date());
        userEntity.setToken(JwtTokenProvider.generateToken("Test"));
        userEntity.setIsActive(true);
        return userEntity;
    }


}
