package com.zimmer.FactoryFlow.dto;

import jakarta.validation.constraints.NotBlank;

public record PermissionRequestDTO(@NotBlank String name,
                                    @NotBlank String description) {
}
