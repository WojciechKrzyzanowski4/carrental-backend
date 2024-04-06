package com.wkrzyz.service.impl;

import com.wkrzyz.dto.ReservationDTO;
import com.wkrzyz.entity.OfferEntity;
import com.wkrzyz.entity.ReservationEntity;
import com.wkrzyz.exception.NotFoundException;
import com.wkrzyz.mapper.ReservationMapper;
import com.wkrzyz.repository.OfferEntityRepository;
import com.wkrzyz.repository.ReservationEntityRepository;
import com.wkrzyz.repository.UserEntityRepository;
import com.wkrzyz.service.ReservationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationEntityRepository reservationEntityRepository;

    private final OfferEntityRepository offerEntityRepository;

    private final UserEntityRepository userEntityRepository;

    private final ReservationMapper reservationMapper;
    @Override
    public List<ReservationDTO> findAll() {
        return reservationEntityRepository.findAll().stream()
                .map(reservationMapper::fromReservationEntityToReservationDTO)
                .toList();
    }

    @Override
    public ReservationEntity saveReservation(ReservationDTO reservationDTO) {
        ReservationEntity reservationEntity = reservationMapper.fromReservationDTOTOReservationEntity(reservationDTO);
        reservationEntityRepository.save(reservationEntity);
        return reservationEntity;
    }
}
