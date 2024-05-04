package com.wkrzyz.dto;


import java.sql.Date;

public record ReservationDTO(Long id,
                             Date reservationDate,
                             UserDTO user,
                             OfferDTO offer



) {
}