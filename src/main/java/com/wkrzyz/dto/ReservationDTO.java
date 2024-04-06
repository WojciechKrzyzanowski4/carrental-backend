package com.wkrzyz.dto;

import jakarta.validation.constraints.NotEmpty;

import java.sql.Date;

public record ReservationDTO(Long id,
                             Date reservationDate,
                             UserDTO user,

                             OfferDTO offer



) {
}