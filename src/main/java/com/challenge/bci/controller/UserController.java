package com.challenge.bci.controller;

import com.challenge.bci.dto.RegisterResponseDTO;
import com.challenge.bci.dto.UserDTO;
import com.challenge.bci.serviceInterface.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign_up")
    public ResponseEntity<RegisterResponseDTO> registeruser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok().body(userService.registerUser(userDTO));
    }


}
