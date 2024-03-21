package com.wkrzyz.dto;

import jakarta.validation.constraints.NotEmpty;

public record OfferDTO(Long id,
                       @NotEmpty(message="offer should have a name") String name,
                       @NotEmpty(message="offer should have a description") String description,
                       @NotEmpty(message="offer should have a price") Double price,
                       @NotEmpty(message="offer should have a price") String model,
                       @NotEmpty(message="offer should have a price") String brand,
                       @NotEmpty(message="offer should have a price") String year



) {
}
