package com.zimmer.FactoryFlow.dto;

import jakarta.validation.constraints.NotBlank;

public record RoleRequestDTO(@NotBlank String name,
                              @NotBlank String description) {
}
