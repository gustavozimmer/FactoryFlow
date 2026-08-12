package com.zimmer.FactoryFlow.dto;

import jakarta.validation.constraints.NotBlank;

public record UserResponseDTO(@NotBlank String name,
                              @NotBlank Long id,
                              @NotBlank String edv) {

}

