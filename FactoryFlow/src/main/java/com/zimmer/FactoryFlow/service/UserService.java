package com.zimmer.FactoryFlow.service;

import com.zimmer.FactoryFlow.dto.UserRequestDTO;
import com.zimmer.FactoryFlow.dto.UserResponseDTO;
import com.zimmer.FactoryFlow.entity.RoleEntity;
import com.zimmer.FactoryFlow.entity.UserEntity;
import com.zimmer.FactoryFlow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;



    public UserResponseDTO createUser(UserRequestDTO dto){
        UserEntity userEntity = new UserEntity();
        userEntity.setName(dto.name());
        userEntity.setActive(true);
        userEntity.setEdv(dto.edv());
//        userEntity.setRole(new RoleEntity());
        userEntity.setPasswordHash(dto.password());
        var savedUser = userRepository.save(userEntity);
        return new UserResponseDTO(savedUser.getName(), savedUser.getId(), savedUser.getEdv());


    }
}