package com.wkrzyz.service;

import com.wkrzyz.dto.OfferDTO;
import com.wkrzyz.dto.ReservationDTO;
import com.wkrzyz.entity.ReservationEntity;

import java.util.List;

public interface ReservationService {


    List<ReservationDTO> findAll();

    ReservationEntity saveReservation(ReservationDTO reservationDTO);

    void saveReservation(ReservationEntity reservationEntity);

    ReservationEntity findById(Long id);
}
