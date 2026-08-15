package com.zimmer.FactoryFlow.controller;

import com.zimmer.FactoryFlow.dto.PermissionRequestDTO;
import com.zimmer.FactoryFlow.dto.PermissionResponseDTO;
import com.zimmer.FactoryFlow.dto.PermissionUpdateDTO;
import com.zimmer.FactoryFlow.service.PermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping
    public List<PermissionResponseDTO> getAllPermissions() {

        return permissionService.getAllPermissions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PermissionResponseDTO> getPermissionById(@PathVariable("id") Long id){
        var permission = permissionService.getPermissionById(id);

        return ResponseEntity.ok(permission);
    }

    @PostMapping()
    public ResponseEntity<PermissionResponseDTO> createPermission(@RequestBody @Valid PermissionRequestDTO dto){
        var permission = permissionService.createPermission(dto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(permission.id()).toUri();

        return ResponseEntity.created(uri).body(permission);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionResponseDTO> updatePermissionById(@PathVariable("id") Long id, @RequestBody @Valid PermissionRequestDTO dto){
        var permission = permissionService.updatePermissionById(id,dto);

        return ResponseEntity.ok(permission);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<PermissionResponseDTO> partialUpdatePermissionById(@PathVariable("id") Long id, @RequestBody PermissionUpdateDTO dto){
        var permission = permissionService.partialUpdatePermissionById(id, dto);

        return ResponseEntity.ok(permission);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermissionById(@PathVariable("id") Long id){
        permissionService.deletePermissionById(id);

        return ResponseEntity.noContent().build();
    }

}
