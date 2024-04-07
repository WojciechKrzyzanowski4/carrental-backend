package com.wkrzyz.controller;

import com.wkrzyz.dto.ReservationDTO;
import com.wkrzyz.entity.OfferEntity;
import com.wkrzyz.entity.ReservationEntity;
import com.wkrzyz.entity.UserEntity;
import com.wkrzyz.exception.NotFoundException;
import com.wkrzyz.service.OAuth2Service;
import com.wkrzyz.service.OfferService;
import com.wkrzyz.service.ReservationService;
import com.wkrzyz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.control.MappingControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * REST controller for the reservation
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
@CrossOrigin
public class ReservationController {

    private final ReservationService reservationService;

    private final UserService userService;

    private final OAuth2Service oAuth2Service;

    private final OfferService offerService;
    /**
     * This method returns all the reservations in the system
     */
    @GetMapping
    List<ReservationDTO> findAll(){
        return reservationService.findAll();
    }
    /**
     * This method creates a reservation for an offer
     * @param id the id of the offer passed in as a path variable
     */
    @PostMapping("create/{id}")
    ResponseEntity<Void> create(@PathVariable Long id, @RequestBody ReservationDTO reservationDTO){
        String email = oAuth2Service.getEmailFromOAuth2Authentication();
        if(email.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        try{
            ReservationEntity reservationEntity = reservationService.saveReservation(reservationDTO);
            OfferEntity offerEntity = offerService.findById(id);
            UserEntity userEntity = userService.findUserByEmail(email).get();
            offerEntity.getReservations().add(reservationEntity);
            userEntity.getReservations().add(reservationEntity);
            reservationEntity.setUser(userEntity);
            reservationEntity.setOffer(offerEntity);
            userService.saveUser(userEntity);
            offerService.saveOfferEntity(offerEntity);
            reservationService.saveReservation(reservationEntity);
        }catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    /**
     * This method sends an email with the invoice for a reservation
     * to the administrator of the system ? I am not sure if this is how
     * we want to handle this, but it is the best that I have for now
     * @param id the id of the offer passed in as a path variable
     */
    @GetMapping("{id}/invoice")
    ResponseEntity<Void> redirectInvoice(@PathVariable Long id){
        try{
            ReservationEntity reservationEntity = reservationService.findById(id);
            System.out.println(reservationEntity.getId());
            System.out.println(reservationEntity.getReservationDate());

            /*
            send the email to the public mailbox of the system
            */
        }catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
