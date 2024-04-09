package com.wkrzyz.service.impl;

import com.wkrzyz.dto.OfferDTO;
import com.wkrzyz.dto.ReservationDTO;
import com.wkrzyz.dto.UserDTO;
import com.wkrzyz.entity.UserEntity;
import com.wkrzyz.exception.NotFoundException;
import com.wkrzyz.mapper.OfferMapper;
import com.wkrzyz.mapper.ReservationMapper;
import com.wkrzyz.mapper.UserMapper;
import com.wkrzyz.repository.UserEntityRepository;
import com.wkrzyz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userEntityRepository;

    private final UserMapper userMapper;

    private final OfferMapper offerMapper;

    private final ReservationMapper reservationMapper;

    @Override
    public Optional<UserEntity> findUserByEmail(String email) {
        return userEntityRepository.findByEmail(email);
    }

    @Override
    public UserDTO findUserDTObyEmail(String email) {
        return userMapper.fromUserEntityToUserDTO(userEntityRepository.findByEmail(email)
                .orElseThrow(()->new NotFoundException("User not found by email = " + email)));
    }

    @Override
    public void saveUser(UserEntity userEntity) {
        userEntityRepository.save(userEntity);
    }

    @Override
    public List<OfferDTO> findTheUserLikedOffers(String email) {
        List<OfferDTO> list = new ArrayList<>();
        UserEntity userEntity = userEntityRepository.findByEmail(email)
                .orElseThrow(()->new NotFoundException("error"));
        list = userEntity.getLikedOffers().stream().map(offerMapper::fromOfferEntityToOfferDTO).toList();
        return list;
    }



    @Override
    public List<ReservationDTO> findTheUserReservations(String email) {
        List<ReservationDTO> list = new ArrayList<>();
        UserEntity userEntity = userEntityRepository.findByEmail(email)
                .orElseThrow(()->new NotFoundException("error"));
        list = userEntity.getReservations().stream().map(reservationMapper::fromReservationEntityToReservationDTO).toList();
        return list;
    }
}
