package com.zimmer.FactoryFlow.service;

import com.zimmer.FactoryFlow.dto.UserRequestDTO;
import com.zimmer.FactoryFlow.dto.UserResponseDTO;
import com.zimmer.FactoryFlow.entity.RoleEntity;
import com.zimmer.FactoryFlow.entity.UserEntity;
import com.zimmer.FactoryFlow.repository.RoleRepository;
import com.zimmer.FactoryFlow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;



    public UserResponseDTO createUser(UserRequestDTO dto){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(dto.name());
        userEntity.setActive(true);
        userEntity.setEdv(dto.edv());
        RoleEntity role = roleRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Role USER não encontrada"));
        userEntity.setRole(role);
        userEntity.setPasswordHash(dto.password());
        var savedUser = userRepository.save(userEntity);
        return new UserResponseDTO(savedUser.getName(), savedUser.getId(), savedUser.getEdv());


    }
}