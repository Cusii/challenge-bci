package com.challenge.bci.controller;

import com.challenge.bci.dto.RegisterResponseDTO;
import com.challenge.bci.dto.UserDTO;
import com.challenge.bci.serviceInterface.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private IUserService userService;

    @InjectMocks
    private UserController userController;


    @Before
    public void setUp() {
        when(userService.registerUser(any(UserDTO.class)))
                .thenReturn(new RegisterResponseDTO());
    }

    @Test
    public void testRegisterUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("test");
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("password");

        ResponseEntity<RegisterResponseDTO> response = userController.registeruser(userDTO);

        verify(userService, times(1)).registerUser(userDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}