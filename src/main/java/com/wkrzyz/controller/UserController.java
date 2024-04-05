package com.wkrzyz.controller;

import com.wkrzyz.dto.CarDTO;
import com.wkrzyz.dto.OfferDTO;
import com.wkrzyz.dto.UserDTO;
import com.wkrzyz.entity.OfferEntity;
import com.wkrzyz.entity.UserEntity;
import com.wkrzyz.exception.NotFoundException;
import com.wkrzyz.service.OAuth2Service;
import com.wkrzyz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
     * This method returns all the offer the currently logged-in user likes
     * */
    @GetMapping("liked-offers")
    public List<OfferDTO> getLikedOffersForCurrentlyLoggedInUser(){
        String email = oAuth2Service.getEmailFromOAuth2Authentication();
        if(email.isEmpty()){
            throw new NotFoundException("authentication failed");
        }
        return userService.findTheUserLikedOffers(email);
    }

    @PostMapping("/edit")
    public ResponseEntity<Void> editUser(@RequestBody UserDTO userDTO){
        try{
            userService.updateUser(userDTO);
        }catch(NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
