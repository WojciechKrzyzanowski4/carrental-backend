package com.wkrzyz.service.impl;

import com.wkrzyz.dto.OfferDTO;
import com.wkrzyz.dto.UserDTO;
import com.wkrzyz.entity.UserEntity;
import com.wkrzyz.exception.NotFoundException;
import com.wkrzyz.mapper.OfferMapper;
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
                .orElseThrow(()->new NotFoundException("no nie update sie"));
        list = userEntity.getLikedOffers().stream().map(offerMapper::fromOfferEntityToOfferDTO).toList();
        return list;
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        if(userEntityRepository.findById(userDTO.id()).isEmpty()){
            throw new NotFoundException("user was not found in the database");
        }
        UserEntity currentUser = userEntityRepository.findById(userDTO.id()).get();

        if(userEntityRepository.findByEmail(userDTO.email()).isPresent()){
            //the email is already in the database, so it can not be set as the new email
            currentUser.setName(userDTO.name());
            userEntityRepository.save(currentUser);
            return;
        }
        currentUser.setName(userDTO.name());
        currentUser.setEmail(userDTO.email());
        userEntityRepository.save(currentUser);
    }
}
