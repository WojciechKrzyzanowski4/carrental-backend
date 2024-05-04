package com.wkrzyz.service.impl;

import com.wkrzyz.dto.ReservationDTO;
import com.wkrzyz.entity.ReservationEntity;
import com.wkrzyz.exception.NotFoundException;
import com.wkrzyz.mapper.ReservationMapper;
import com.wkrzyz.repository.ReservationEntityRepository;
import com.wkrzyz.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationEntityRepository reservationEntityRepository;

    private final ReservationMapper reservationMapper;
    @Override
    public List<ReservationDTO> findAll() {
        return reservationEntityRepository.findAll().stream()
                .map(reservationMapper::fromReservationEntityToReservationDTO)
                .toList();
    }

    @Override
    public ReservationEntity saveReservation(ReservationDTO reservationDTO) {
        ReservationEntity reservationEntity = reservationMapper.fromReservationDTOToReservationEntity(reservationDTO);
        reservationEntityRepository.save(reservationEntity);
        return reservationEntity;
    }
    @Override
    public void saveReservation(ReservationEntity reservationEntity) {
        reservationEntityRepository.save(reservationEntity);
    }

    @Override
    public ReservationEntity findById(Long id) {
        return reservationEntityRepository.findById(id).orElseThrow(()-> new NotFoundException("element not found"));
    }
}
