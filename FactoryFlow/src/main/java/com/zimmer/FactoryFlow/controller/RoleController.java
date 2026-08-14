package com.zimmer.FactoryFlow.controller;

import com.zimmer.FactoryFlow.dto.RoleRequestDTO;
import com.zimmer.FactoryFlow.dto.RoleResponseDTO;
import com.zimmer.FactoryFlow.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleResponseDTO> createRole(@RequestBody @Valid RoleRequestDTO dto){
        var role = roleService.createRole(dto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(role.id()).toUri();

        return ResponseEntity.created(uri).body(role);

    }

}
