package com.zimmer.FactoryFlow.dto;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateDTO(String name,
                            String edv) {
}
