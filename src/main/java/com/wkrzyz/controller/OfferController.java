package com.wkrzyz.controller;

import com.wkrzyz.dto.OfferDTO;
import com.wkrzyz.dto.ReservationDTO;
import com.wkrzyz.entity.OfferEntity;
import com.wkrzyz.entity.UserEntity;
import com.wkrzyz.exception.NotFoundException;
import com.wkrzyz.service.OAuth2Service;
import com.wkrzyz.service.OfferService;
import com.wkrzyz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    private final OAuth2Service oAuth2Service;

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
            throw new NotFoundException("failed to create an offer");
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    /**
     * This method deletes and offer record from the database based on the id
     * @param id the id of the offer passed in as a path variable
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Long id){
        try{
            offerService.delete(id);
        }catch (NotFoundException e){
            throw new NotFoundException("failed to delete an offer with id: " + id);
        }
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
            throw new NotFoundException("failed to edit the offer");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    /**
     * This method gets the currently logged-in user and adds the offer of choice to the liked offers
     * while also adding the user to the users who like this particular offer
     * @param id the id of the offer passed in as a path variable
     */
    @GetMapping("{id}/like")
    public ResponseEntity<Void> likeOffer(@PathVariable Long id){
        String email = oAuth2Service.getEmailFromOAuth2Authentication();
        if(email.isEmpty()){
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        }
        try{
            UserEntity currentUser = userService.findUserByEmail(email).orElseThrow();
            OfferEntity currentOffer = offerService.findById(id);
            currentUser.getLikedOffers().add(currentOffer);
            currentOffer.getLikedByUsers().add(currentUser);
            offerService.saveOfferEntity(currentOffer);
            userService.saveUser(currentUser);
        }catch(NoSuchElementException e){
            throw new NotFoundException("failed to like the offer with id: " + id);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    /**
     * This method gets the currently logged-in user and removes the offer of choice from the liked offers
     * while also removing the users from the users who like this particular offer
     * @param id the id of the offer passed in as a path variable
     */
    @GetMapping("{id}/dislike")
    public ResponseEntity<Void> dislikeOffer(@PathVariable Long id){

        String email = oAuth2Service.getEmailFromOAuth2Authentication();
        if(email.isEmpty()){
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
        }
        try{
            UserEntity currentUser = userService.findUserByEmail(email).orElseThrow();
            OfferEntity currentOffer = offerService.findById(id);
            currentUser.getLikedOffers().remove(currentOffer);
            currentOffer.getLikedByUsers().remove(currentUser);
            offerService.saveOfferEntity(currentOffer);
            userService.saveUser(currentUser);
        }catch(NoSuchElementException e) {
            throw new NotFoundException("failed to dislike the offer with id: " + id);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }
    /**
     * This method returns all the reservations regarding a particular offer
     * @param id the id of the offer passed in as a path variable
     */
    @GetMapping("/{id}/reservations")
    public List<ReservationDTO> getReservationsForOffer(@PathVariable Long id){
        return offerService.findTheOfferReservations(id);
    }
}
