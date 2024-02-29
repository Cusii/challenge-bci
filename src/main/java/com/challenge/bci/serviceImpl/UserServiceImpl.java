package com.challenge.bci.serviceImpl;

import com.challenge.bci.dto.RegisterResponseDTO;
import com.challenge.bci.dto.UserDTO;
import com.challenge.bci.entity.PhoneEntity;
import com.challenge.bci.entity.UserEntity;
import com.challenge.bci.exception.RequestException;

import com.challenge.bci.repository.UserRepository;
import com.challenge.bci.serviceInterface.IUserService;
import com.challenge.bci.utils.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;

    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    @Override
    public RegisterResponseDTO registerUser(UserDTO userDTO) {

        Optional<UserEntity> checkEmail = userRepository.findByEmail(userDTO.getEmail());
        log.info("Empezando el servicio UserService");
        if(!isValidEmail(userDTO.getEmail())){
            throw new RequestException(new Date(), 500, "El email no es valido");
        }
        if(checkEmail.isPresent()){
            throw new RequestException(new Date(), 500, "El email existe");
        }else{
            Date momentDate = new Date();
            UserEntity userEntity = UserEntity.builder()
                    .name(userDTO.getName())
                    .email(userDTO.getEmail())
                    .password(userDTO.getPassword())
                    .created(momentDate)
                    .modified(momentDate)
                    .lastLogin(momentDate)
                    .token(JwtTokenProvider.generateToken(userDTO.getName()))
                    .phones(userDTO.getPhones().stream().map(PhoneEntity::fromDtoToEntity).collect(Collectors.toSet()))
                    .isActive(Boolean.TRUE)
                    .build();

            UserEntity savedUser = userRepository.save(userEntity);
            log.info("Finalizando el servicio UserService");
            return RegisterResponseDTO.entityToDTO(savedUser);
        }
    }
    private Boolean isValidEmail(String email) {
        log.info("Validando Email");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
