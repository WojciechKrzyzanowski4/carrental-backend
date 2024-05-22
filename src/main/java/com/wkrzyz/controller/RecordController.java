package com.wkrzyz.controller;

import com.wkrzyz.dto.RecordDTO;
import com.wkrzyz.entity.RecordEntity;
import com.wkrzyz.entity.ReservationEntity;
import com.wkrzyz.entity.ReservationStatus;
import com.wkrzyz.service.RecordService;
import com.wkrzyz.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/record")
@CrossOrigin
public class RecordController {

    private final RecordService recordService;

    private final ReservationService reservationService;

    @GetMapping
    public List<RecordDTO> getAllRecords(){

        return recordService.getAllRecords();
    }

    @GetMapping("/user/{id}")
    public List<RecordDTO> getAllUserRecords(@PathVariable Long id){

        return recordService.getAllUserRecords(id);
    }

    @GetMapping("/offer/{id}")
    public List<RecordDTO> getAllOfferRecords(@PathVariable Long id){

        return recordService.getAllOfferRecords(id);
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<Void> bookRecord(@PathVariable Long id){
        ReservationEntity reservation = reservationService.findById(id);
        ReservationStatus reservationStatus = ReservationStatus.PAYED;
        recordService.bookRecord(reservation, reservationStatus);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelRecord(@PathVariable Long id){
        ReservationEntity reservation = reservationService.findById(id);
        ReservationStatus reservationStatus = ReservationStatus.CANCELED;
        recordService.bookRecord(reservation, reservationStatus);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }



}
