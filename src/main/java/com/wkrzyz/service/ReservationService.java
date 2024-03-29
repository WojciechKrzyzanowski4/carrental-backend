package com.wkrzyz.service;

import com.wkrzyz.dto.ReservationDTO;
import com.wkrzyz.entity.ReservationEntity;

import java.util.List;

public interface ReservationService {


    List<ReservationDTO> findAll();
}
