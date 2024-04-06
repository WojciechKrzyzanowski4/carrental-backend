package com.wkrzyz.service.impl;

import com.wkrzyz.dto.OfferDTO;
import com.wkrzyz.dto.ReservationDTO;
import com.wkrzyz.entity.CarEntity;
import com.wkrzyz.entity.OfferEntity;
import com.wkrzyz.entity.ReservationEntity;
import com.wkrzyz.entity.UserEntity;
import com.wkrzyz.exception.NotFoundException;
import com.wkrzyz.mapper.OfferMapper;
import com.wkrzyz.mapper.ReservationMapper;
import com.wkrzyz.repository.CarEntityRepository;
import com.wkrzyz.repository.OfferEntityRepository;
import com.wkrzyz.repository.UserEntityRepository;
import com.wkrzyz.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferEntityRepository offerEntityRepository;
    private final CarEntityRepository carEntityRepository;
    private final UserEntityRepository userEntityRepository;
    private final OfferMapper offerMapper;
    private final ReservationMapper reservationMapper;

    @Override
    public void saveOffer(OfferDTO offerDTO) {
        Long carId = offerDTO.carId();
        OfferEntity offerEntity = offerMapper.fromOfferDTOToOfferEntity(offerDTO);
        CarEntity carEntity = carEntityRepository.findById(carId).orElseThrow(NoSuchElementException::new);
        offerEntity.setCar(carEntity);
        offerEntityRepository.save(offerEntity);
    }

    @Override
    public void saveOfferEntity(OfferEntity offerEntity) {
        offerEntityRepository.save(offerEntity);
    }

    @Override
    public void delete(Long id) {
        //we need to make sure to delete the reference created by the likes.
        if(offerEntityRepository.findById(id).isPresent()){
            OfferEntity offer = offerEntityRepository.findById(id).get();
            List<UserEntity> userEntities = offer.getLikedByUsers();
            for(UserEntity u : userEntities){
                u.getLikedOffers().remove(offer);
                userEntityRepository.save(u);
            }
            offer.getLikedByUsers().clear();
            offerEntityRepository.deleteById(id);
        }else{
            throw new NotFoundException("bad path variable");
        }

    }

    @Override
    public void put(OfferDTO offerDTO) {

        Long carId = offerDTO.carId();
        OfferEntity offerEntity = offerMapper.fromOfferDTOToOfferEntity(offerDTO);
        CarEntity carEntity = carEntityRepository.findById(carId).orElseThrow(NoSuchElementException::new);
        offerEntity.setCar(carEntity);
        if(offerEntityRepository.findById(offerEntity.getId()).isPresent()){
            offerEntityRepository.save(offerEntity);
        }else{
            throw new NoSuchElementException("the object was already removed");
        }

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


    @Override
    public List<ReservationDTO> findTheOfferReservations(Long id) {
        List<ReservationDTO> list = new ArrayList<>();
        OfferEntity offerEntity = offerEntityRepository.findById(id)
                .orElseThrow(()->new NotFoundException("error"));
        list = offerEntity.getReservations().stream().map(reservationMapper::fromReservationEntityToReservationDTO).toList();
        return list;
    }
}
