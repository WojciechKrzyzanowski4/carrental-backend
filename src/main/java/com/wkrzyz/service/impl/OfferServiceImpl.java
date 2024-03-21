package com.wkrzyz.service.impl;

import com.wkrzyz.dto.OfferDTO;
import com.wkrzyz.entity.OfferEntity;
import com.wkrzyz.exception.NotFoundException;
import com.wkrzyz.mapper.OfferMapper;
import com.wkrzyz.repository.OfferEntityRepository;
import com.wkrzyz.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferEntityRepository offerEntityRepository;
    private final OfferMapper offerMapper;

    @Override
    public void saveOffer(OfferDTO offerDTO) {
        OfferEntity offerEntity = offerMapper.fromOfferDTOToOfferEntity(offerDTO);
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
