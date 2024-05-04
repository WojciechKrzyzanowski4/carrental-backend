package com.wkrzyz.dto;

import jakarta.validation.constraints.NotEmpty;

import java.sql.Date;

public record RecordDTO(Long id,
                        @NotEmpty(message = "The record should have a status") Integer status,
                        Date recordDate,
                        UserDTO user,
                        OfferDTO offer){
}
