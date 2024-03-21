package com.wkrzyz.service;

import com.wkrzyz.dto.CarDTO;
import com.wkrzyz.dto.OfferDTO;
import com.wkrzyz.entity.OfferEntity;
import com.wkrzyz.entity.UserEntity;

import java.util.List;

public interface OfferService {

    void saveOffer(OfferDTO offerDTO);

    List<OfferDTO> findAll();

    OfferEntity findById(Long id);
}
