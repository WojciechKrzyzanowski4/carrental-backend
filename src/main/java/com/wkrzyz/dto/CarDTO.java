package com.wkrzyz.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CarDTO(Long id,
                     @NotEmpty(message="car should have a brand") String brand,
                     @NotEmpty(message="car should have a model") String model,
                     @NotNull(message="car should have a year") Integer year) {
}
