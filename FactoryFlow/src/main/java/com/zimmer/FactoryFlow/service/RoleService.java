package com.zimmer.FactoryFlow.service;

import com.zimmer.FactoryFlow.dto.PermissionResponseDTO;
import com.zimmer.FactoryFlow.dto.RoleRequestDTO;
import com.zimmer.FactoryFlow.dto.RoleResponseDTO;
import com.zimmer.FactoryFlow.dto.RoleUpdateDTO;
import com.zimmer.FactoryFlow.entity.PermissionEntity;
import com.zimmer.FactoryFlow.entity.PermissionRoleEntity;
import com.zimmer.FactoryFlow.entity.RoleEntity;
import com.zimmer.FactoryFlow.repository.PermissionRepository;
import com.zimmer.FactoryFlow.repository.PermissionRoleRepository;
import com.zimmer.FactoryFlow.repository.RoleRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final PermissionRoleRepository permissionRoleRepository;

    public RoleResponseDTO createRole(RoleRequestDTO dto){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(dto.name());
        roleEntity.setDescription(dto.description());
        var savedRole = roleRepository.save(roleEntity);

        return new RoleResponseDTO(savedRole.getName(), savedRole.getDescription(), savedRole.getId());
    }

    public RoleResponseDTO getRoleById(Long id){
        var role = roleRepository.findById(id).orElse(null);

        return new RoleResponseDTO(role.getName(), role.getDescription(), role.getId());
    }

    public List<RoleResponseDTO> getAllRoles(){
        List<RoleResponseDTO> roles;
        roles = roleRepository
                .findAll()
                .stream()
                .map(role -> new RoleResponseDTO(
                        role.getName(),
                        role.getDescription(),
                        role.getId()
                ))
                .toList();

        return roles;
    }

    public RoleResponseDTO updateRoleById(Long id, RoleRequestDTO dto){
        var role = roleRepository.findById(id).orElse(null);
        role.setName(dto.name());
        role.setDescription(dto.description());

        var savedPermission = roleRepository.save(role);

        return new RoleResponseDTO(savedPermission.getName(), savedPermission.getDescription(), savedPermission.getId());
    }

    public RoleResponseDTO partialUpdateRoleById(Long id, RoleUpdateDTO dto){
        var role = roleRepository.findById(id).orElse(null);

        if(dto.name() != null){
            role.setName(dto.name());
        }
        if (dto.description() != null){
            role.setDescription(dto.description());
        }

        var savedPermission = roleRepository.save(role);

        return new RoleResponseDTO(savedPermission.getName(), savedPermission.getDescription(), savedPermission.getId());
    }

    public void deleteRole(Long id){
        var role = roleRepository.findById(id).orElse(null);
        roleRepository.delete(role);
    }


    public void addPermissionToRole(Long roleId, Long permissionId){
        PermissionRoleEntity permissionRoleEntity = new PermissionRoleEntity();
        permissionRoleEntity.setPermission(permissionRepository.findById(permissionId).orElse(null));
        permissionRoleEntity.setRole(roleRepository.findById(roleId).orElse(null));

        permissionRoleRepository.save(permissionRoleEntity);

    }


    public List<PermissionResponseDTO> getAllPermissionsByRole(Long roleId) {

        return permissionRoleRepository
                .findByRoleId(roleId)
                .stream()
                .map(pr -> new PermissionResponseDTO(
                        pr.getPermission().getName(),
                        pr.getPermission().getDescription(),
                        pr.getPermission().getId()
                ))
                .toList();
    }

}
