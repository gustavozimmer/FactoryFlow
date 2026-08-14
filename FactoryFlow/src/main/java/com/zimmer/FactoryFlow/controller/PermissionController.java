package com.zimmer.FactoryFlow.controller;

import com.zimmer.FactoryFlow.dto.PermissionRequestDTO;
import com.zimmer.FactoryFlow.dto.PermissionResponseDTO;
import com.zimmer.FactoryFlow.service.PermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/permission")
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

    @PostMapping
    public ResponseEntity<PermissionResponseDTO> createPermission(@RequestBody @Valid PermissionRequestDTO dto){
        var permission = permissionService.createPermission(dto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(permission.id()).toUri();

        return ResponseEntity.created(uri).body(permission);
    }

}
