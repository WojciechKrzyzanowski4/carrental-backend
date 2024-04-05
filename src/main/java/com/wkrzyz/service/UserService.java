package com.wkrzyz.service;

import com.wkrzyz.dto.OfferDTO;
import com.wkrzyz.dto.UserDTO;
import com.wkrzyz.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserEntity> findUserByEmail(String email);

    UserDTO findUserDTObyEmail(String email);

    void saveUser(UserEntity userEntity);

    List<OfferDTO> findTheUserLikedOffers(String email);

    void updateUser(UserDTO userDTO);
}
