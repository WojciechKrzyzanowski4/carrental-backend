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
/**
 * REST controller for the offer
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/offer")
@CrossOrigin
public class OfferController {

    private final UserService userService;

    private final OfferService offerService;

    /**
     * This method returns all the offer records from the database
     * */
    @GetMapping
    public List<OfferDTO> getAllOffers(){
        return offerService.findAll();
    }
    /**
     * This method creates a new offer record in the database
     * @param offerDTO the data about the offer passed in the body of the post request
     * */
    @PostMapping("/create")
    public ResponseEntity<Void> createOffer(@Validated @RequestBody OfferDTO offerDTO){
        System.out.println("creating an offer");
        try{
            offerService.saveOffer(offerDTO);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    /**
     * This method deletes and offer record from the database based on the id
     * @param id the id of the offer passed in as a path variable
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Long id){
        offerService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    /**
     * This method updated an offer record in the database in said record still exists
     * @param offerDTO the data about the offer passed in the body of the post request
     * */
    @PutMapping ("/edit")
    public ResponseEntity<Void> edit(@Validated @RequestBody OfferDTO offerDTO){
        try{
            offerService.put(offerDTO);
        }catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    /**
     * This method finds all the users who like a particular offer
     * @param id the id of the offer passed in as a path variable
     * */
    @GetMapping("/likes/{id}")
    public ResponseEntity<Void> seeWhoLikesIt(@PathVariable Long id){
        List<UserEntity> users = offerService.findById(id).getLikedByUsers();
        for(UserEntity u : users){
            System.out.println(u.getEmail());

            //we send the email!!

        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    /**
     * This method get the currently logged-in user and adds the offer of choice to the liked offers
     * while also adding the user to the users who like this particular offer
     * @param id the id of the offer passed in as a path variable
     */
    @GetMapping("{id}/like")
    public ResponseEntity<Void> likeOffer(@PathVariable Long id){

        String email;
        UserEntity currentUser;

        SecurityContext securityContextHolder = SecurityContextHolder.getContext();
        Authentication authentication = securityContextHolder.getAuthentication();
        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        if(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId().equals("github")){
            email = oAuth2AuthenticationToken.getPrincipal().getAttributes().get("login").toString();
        }
        else{
            email = "";
        }
        try{
            System.out.println(offerService.findById(id).getDescription());
            currentUser = userService.findUserByEmail(email).orElseThrow();
            OfferEntity currentOffer = offerService.findById(id);
            currentUser.getLikedOffers().add(currentOffer);
            currentOffer.getLikedByUsers().add(currentUser);
            offerService.saveOfferEntity(currentOffer);
            userService.saveUser(currentUser);
        }catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
}
