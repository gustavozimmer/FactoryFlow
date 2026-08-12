package com.zimmer.FactoryFlow.service;

import com.zimmer.FactoryFlow.dto.RoleRequestDTO;
import com.zimmer.FactoryFlow.dto.RoleResponseDTO;
import com.zimmer.FactoryFlow.entity.RoleEntity;
import com.zimmer.FactoryFlow.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public RoleResponseDTO createRole(RoleRequestDTO dto){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(dto.name());
        roleEntity.setDescription(dto.description());
        var savedRole = roleRepository.save(roleEntity);

        return new RoleResponseDTO(savedRole.getName(), savedRole.getDescription(), savedRole.getId());
    }

}
