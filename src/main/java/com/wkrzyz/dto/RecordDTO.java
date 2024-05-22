package com.wkrzyz.dto;

import com.wkrzyz.entity.ReservationStatus;
import jakarta.validation.constraints.NotEmpty;

import java.sql.Date;

public record RecordDTO(Long id,
                        @NotEmpty(message = "The record should have a status") String status,
                        Date recordDate,
                        UserDTO user,
                        OfferDTO offer){
}
