package com.zimmer.FactoryFlow.dto;

import jakarta.validation.constraints.NotBlank;

public record RoleResponseDTO(@NotBlank String name,
                              @NotBlank String description,
                              @NotBlank Long id){
}
