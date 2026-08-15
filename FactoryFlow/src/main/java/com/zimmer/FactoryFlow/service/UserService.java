package com.zimmer.FactoryFlow.service;

import com.zimmer.FactoryFlow.dto.UserRequestDTO;
import com.zimmer.FactoryFlow.dto.UserResponseDTO;
import com.zimmer.FactoryFlow.dto.UserUpdateDTO;
import com.zimmer.FactoryFlow.entity.RoleEntity;
import com.zimmer.FactoryFlow.entity.UserEntity;
import com.zimmer.FactoryFlow.repository.RoleRepository;
import com.zimmer.FactoryFlow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final Long DEFAULT_ROLE_ID = 1L;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;



    public UserResponseDTO createUser(UserRequestDTO dto){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(dto.name());
        userEntity.setActive(true);
        userEntity.setEdv(dto.edv());

        RoleEntity role = roleRepository.findById(DEFAULT_ROLE_ID)
                .orElseThrow(() -> new RuntimeException("Role USER não encontrada"));
        userEntity.setRole(role);

        String passwordHash = passwordEncoder.encode(dto.password());

        userEntity.setPasswordHash(passwordHash);
        var savedUser = userRepository.save(userEntity);

        return new UserResponseDTO(savedUser.getName(), savedUser.getId(), savedUser.getEdv());
    }


    public UserResponseDTO getUserById(Long id){
        UserEntity user = userRepository.findById(id).orElse(null);

        return new UserResponseDTO(user.getName(), user.getId(), user.getEdv());

    }

    public List<UserResponseDTO> getAllUsers(){
        List<UserResponseDTO> users;
        users = userRepository
                .findByActiveTrue()
                .stream()
                .map(user -> new UserResponseDTO(
                user.getName(),
                user.getId(),
                user.getEdv()))
                .toList();

        return users;
    }

    public UserResponseDTO updateUserById(Long id, UserRequestDTO dto){
        UserEntity user = userRepository.findById(id).orElse(null);

        user.setName(dto.name());
        user.setEdv(dto.edv());

        UserEntity savedUser = userRepository.save(user);


        return new UserResponseDTO(savedUser.getName(), savedUser.getId(), savedUser.getEdv());
    }

    public UserResponseDTO partialUpdateUserById(Long id, UserUpdateDTO dto){
        UserEntity user = userRepository.findById(id).orElse(null);
        if (dto.name() != null){
            user.setName(dto.name());
        }
        if (dto.edv() != null){
            user.setEdv(dto.edv());
        }

        UserEntity savedUser = userRepository.save(user);


        return new UserResponseDTO(savedUser.getName(), savedUser.getId(), savedUser.getEdv());
    }

    public void deleteUserById(Long id) {
        UserEntity user = userRepository.findById(id).orElse(null);
        user.setActive(false);
        userRepository.save(user);
    }




}