package com.wkrzyz.controller;


import com.wkrzyz.dto.OfferDTO;
import com.wkrzyz.dto.ReservationDTO;
import com.wkrzyz.dto.UserDTO;
import com.wkrzyz.exception.NotFoundException;
import com.wkrzyz.service.OAuth2Service;
import com.wkrzyz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * REST controller for the user
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    private final UserService userService;

    private final OAuth2Service oAuth2Service;

    /**
     * This method return the currently logged-in user as a DTO object
     * */
    @GetMapping
    public UserDTO findCurrentlyLoggedInUser(){
        String email = oAuth2Service.getEmailFromOAuth2Authentication();
        if(email.isEmpty()){
            throw new NotFoundException("authentication failed");
        }
        return userService.findUserDTObyEmail(email);
    }

    /**
     * This method returns all the offers the currently logged-in user likes
     * */
    @GetMapping("liked-offers")
    public List<OfferDTO> getLikedOffersForCurrentlyLoggedInUser(){
        String email = oAuth2Service.getEmailFromOAuth2Authentication();
        if(email.isEmpty()){
            throw new NotFoundException("authentication failed");
        }
        return userService.findTheUserLikedOffers(email);
    }
    /**
     * This method returns all the reservations the currently logged-in user has
     * */
    @GetMapping("reservations")
    public List<ReservationDTO> getReservationsForCurrentlyLoggedInUser(){
        String email = oAuth2Service.getEmailFromOAuth2Authentication();
        if(email.isEmpty()){
            throw new NotFoundException("authentication failed");
        }
        return userService.findTheUserReservations(email);
    }

}
