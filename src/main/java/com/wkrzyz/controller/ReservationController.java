package com.wkrzyz.controller;

import com.wkrzyz.dto.ReservationDTO;
import com.wkrzyz.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
@CrossOrigin
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    List<ReservationDTO> findAll(){
        return reservationService.findAll();
    }



}
