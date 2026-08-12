package com.zimmer.FactoryFlow.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(@NotBlank String name,
                             @NotBlank String password,
                             @NotBlank String edv) {
}