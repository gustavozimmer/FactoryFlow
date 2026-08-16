package com.zimmer.FactoryFlow.controller;

import com.zimmer.FactoryFlow.dto.RoleRequestDTO;
import com.zimmer.FactoryFlow.dto.RoleResponseDTO;
import com.zimmer.FactoryFlow.dto.RoleUpdateDTO;
import com.zimmer.FactoryFlow.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public List<RoleResponseDTO> getAllRoles(){

        return roleService.getAllRoles();
    }


    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> getRoleById(@PathVariable("id") Long id){
        var role = roleService.getRoleById(id);

        return ResponseEntity.ok(role);
    }


    @PostMapping
    public ResponseEntity<RoleResponseDTO> createRole(@RequestBody @Valid RoleRequestDTO dto){
        var role = roleService.createRole(dto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(role.id()).toUri();

        return ResponseEntity.created(uri).body(role);
    }


    @PutMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> updateRoleById(@PathVariable("id") Long id, @RequestBody @Valid RoleRequestDTO dto){
        var role = roleService.updateRoleById(id, dto);

        return ResponseEntity.ok(role);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> partialUpdateRoleById(@PathVariable("id") Long id, @RequestBody @Valid RoleUpdateDTO dto){
        var role = roleService.partialUpdateRoleById(id, dto);

        return ResponseEntity.ok(role);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoleById(@PathVariable("id") Long id){
        roleService.deleteRole(id);

        return ResponseEntity.noContent().build();
    }
}
