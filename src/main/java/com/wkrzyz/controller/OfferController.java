package com.wkrzyz.controller;

import com.wkrzyz.dto.CarDTO;
import com.wkrzyz.dto.OfferDTO;
import com.wkrzyz.entity.CarEntity;
import com.wkrzyz.entity.OfferEntity;
import com.wkrzyz.entity.UserEntity;
import com.wkrzyz.exception.NotFoundException;
import com.wkrzyz.service.CarService;
import com.wkrzyz.service.OfferService;
import com.wkrzyz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/offer")
@CrossOrigin
public class OfferController {

    private final UserService userService;

    private final OfferService offerService;

    @GetMapping
    public List<OfferDTO> getAllOffers(){
        return offerService.findAll();
    }

    @GetMapping("{id}/like")
    public ResponseEntity<Void> likeOffer(@PathVariable Long id){

        String email;
        UserEntity currentUser;
        List<OfferEntity> list = new ArrayList<>();

        SecurityContext securityContextHolder = SecurityContextHolder.getContext();
        Authentication authentication = securityContextHolder.getAuthentication();
        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        if(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId().equals("github")){
            email = oAuth2AuthenticationToken.getPrincipal().getAttributes().get("login").toString();
        }
        else{
            email = "you will not find me in the database";
        }
        try{
            System.out.println(offerService.findById(id).getDescription());
            currentUser = userService.findUserByEmail(email).orElseThrow();
            currentUser.getLikedOffers().add(offerService.findById(id));
            userService.saveUser(currentUser);
        }catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
    @PostMapping("/create")
    public ResponseEntity<Void> createOffer(@Validated @RequestBody OfferDTO offerDTO){
        //hitting the endpoint
        System.out.println("creating an offer");
        try{
            offerService.saveOffer(offerDTO);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
