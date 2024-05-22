package com.wkrzyz.service;

import com.wkrzyz.dto.RecordDTO;
import com.wkrzyz.entity.RecordEntity;
import com.wkrzyz.entity.ReservationEntity;
import com.wkrzyz.entity.ReservationStatus;

import java.util.List;

public interface RecordService {

    List<RecordDTO> getAllRecords();

    List<RecordDTO> getAllUserRecords(Long id);

    List<RecordDTO> getAllOfferRecords(Long id);

    void bookRecord(ReservationEntity reservationEntity, ReservationStatus status);




}
