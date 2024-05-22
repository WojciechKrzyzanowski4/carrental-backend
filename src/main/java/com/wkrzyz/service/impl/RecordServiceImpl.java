package com.wkrzyz.service.impl;

import com.wkrzyz.dto.RecordDTO;
import com.wkrzyz.entity.RecordEntity;
import com.wkrzyz.entity.ReservationEntity;
import com.wkrzyz.entity.ReservationStatus;
import com.wkrzyz.mapper.RecordMapper;
import com.wkrzyz.repository.RecordEntityRepository;
import com.wkrzyz.repository.ReservationEntityRepository;
import com.wkrzyz.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    private final RecordEntityRepository recordEntityRepository;

    private final ReservationEntityRepository reservationEntityRepository;

    private final RecordMapper recordMapper;

    @Override
    public List<RecordDTO> getAllRecords(){
        return recordEntityRepository.findAll().stream()
                .map(recordMapper::fromRecordEntityToRecordDTO)
                .toList();
    }

    @Override
    public List<RecordDTO> getAllUserRecords(Long id) {
        return recordEntityRepository.findAllByUser_id(id).stream()
                .map(recordMapper::fromRecordEntityToRecordDTO)
                .toList();
    }

    @Override
    public List<RecordDTO> getAllOfferRecords(Long id) {
        return recordEntityRepository.findAllByOffer_id(id).stream()
                .map(recordMapper::fromRecordEntityToRecordDTO)
                .toList();
    }

    @Override
    public void bookRecord(ReservationEntity reservationEntity, ReservationStatus status) {
        RecordEntity recordEntity = new RecordEntity();
        recordEntity.setRecordDate(reservationEntity.getReservationDate());
        recordEntity.setStatus(status);
        recordEntity.setUser(reservationEntity.getUser());
        recordEntity.setOffer(reservationEntity.getOffer());
        recordEntityRepository.save(recordEntity);
        reservationEntityRepository.delete(reservationEntity);
    }


}
