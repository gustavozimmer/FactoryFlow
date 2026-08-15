package com.zimmer.FactoryFlow.service;

import com.zimmer.FactoryFlow.dto.PermissionRequestDTO;
import com.zimmer.FactoryFlow.dto.PermissionResponseDTO;
import com.zimmer.FactoryFlow.dto.PermissionUpdateDTO;
import com.zimmer.FactoryFlow.entity.PermissionEntity;
import com.zimmer.FactoryFlow.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionResponseDTO createPermission(PermissionRequestDTO dto){
        PermissionEntity permissionEntity = new PermissionEntity();
        permissionEntity.setName(dto.name());
        permissionEntity.setDescription(dto.description());
        var savedPermission = permissionRepository.save(permissionEntity);

        return new PermissionResponseDTO(savedPermission.getName(), savedPermission.getDescription(), savedPermission.getId());
    }

    public PermissionResponseDTO getPermissionById(Long id){
        var permission = permissionRepository.findById(id).orElse(null);
        return new PermissionResponseDTO(permission.getName(), permission.getDescription(), permission.getId());
    }

    public List<PermissionResponseDTO> getAllPermissions() {
        List<PermissionResponseDTO> permissions;
        permissions = permissionRepository
                .findAll()
                .stream()
                .map(permission -> new PermissionResponseDTO(
                permission.getName(),
                permission.getDescription(),
                permission.getId()))
                .toList();

        return permissions;
    }

    public PermissionResponseDTO updatePermissionById(Long id, PermissionRequestDTO dto){
        var permission = permissionRepository.findById(id).orElse(null);
        permission.setName(dto.name());
        permission.setDescription(dto.description());
        var savedPermission = permissionRepository.save(permission);

        return new PermissionResponseDTO(savedPermission.getName(), savedPermission.getDescription(), savedPermission.getId());
    }

    public PermissionResponseDTO partialUpdatePermissionById(Long id, PermissionUpdateDTO dto){
        var permission = permissionRepository.findById(id).orElse(null);

        if (dto.name() != null){
            permission.setName(dto.name());
        }
        if (dto.description() != null){
            permission.setDescription(dto.description());
        }

        var savedPermission = permissionRepository.save(permission);

        return new PermissionResponseDTO(savedPermission.getName(), savedPermission.getDescription(), savedPermission.getId());

    }

    public void deletePermissionById(Long id){
        var permission = permissionRepository.findById(id).orElse(null);
        permissionRepository.delete(permission);
    }
}
