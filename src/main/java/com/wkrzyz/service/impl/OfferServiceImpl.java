package com.wkrzyz.service.impl;

import com.wkrzyz.dto.OfferDTO;
import com.wkrzyz.entity.CarEntity;
import com.wkrzyz.entity.OfferEntity;
import com.wkrzyz.exception.NotFoundException;
import com.wkrzyz.mapper.OfferMapper;
import com.wkrzyz.repository.CarEntityRepository;
import com.wkrzyz.repository.OfferEntityRepository;
import com.wkrzyz.repository.UserEntityRepository;
import com.wkrzyz.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferEntityRepository offerEntityRepository;
    private final CarEntityRepository carEntityRepository;
    private final OfferMapper offerMapper;

    @Override
    public void saveOffer(OfferDTO offerDTO) {
        Long carId = offerDTO.carId();
        OfferEntity offerEntity = offerMapper.fromOfferDTOToOfferEntity(offerDTO);
        CarEntity carEntity = carEntityRepository.findById(carId).orElseThrow(NoSuchElementException::new);
        offerEntity.setCar(carEntity);
        offerEntityRepository.save(offerEntity);
    }
    @Override
    public List<OfferDTO> findAll() {
        return offerEntityRepository.findAll().stream()
                .map(offerMapper::fromOfferEntityToOfferDTO)
                .toList();
    }

    @Override
    public OfferEntity findById(Long id) {
        return offerEntityRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Offer not Found by id = " + id));
    }
}
