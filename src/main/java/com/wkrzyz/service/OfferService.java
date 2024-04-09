package com.wkrzyz.service;

import com.wkrzyz.dto.OfferDTO;
import com.wkrzyz.dto.ReservationDTO;
import com.wkrzyz.entity.OfferEntity;

import java.util.List;

public interface OfferService {

    void saveOffer(OfferDTO offerDTO);

    void saveOfferEntity(OfferEntity offerEntity);

    void delete(Long id);

    void put(OfferDTO offerDTO);

    List<OfferDTO> findAll();

    OfferEntity findById(Long id);

    List<ReservationDTO> findTheOfferReservations(Long id);
}
