package com.zimmer.FactoryFlow.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginResquestDTO(@NotBlank String edv,
                               @NotBlank String password) {
}
