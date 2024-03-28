package com.wkrzyz.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record OfferDTO(Long id,
                       @NotEmpty(message="offer should have a name") String name,
                       @NotEmpty(message="offer should have a description") String description,
                       Double price,
                       String model,
                       String brand,
                       String year,
                       Long carId

) {
}
