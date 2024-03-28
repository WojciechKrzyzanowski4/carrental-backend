package com.wkrzyz.controller;

import com.wkrzyz.dto.CarDTO;
import com.wkrzyz.exception.NotFoundException;
import com.wkrzyz.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for the cars entities
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
@CrossOrigin
public class CarController {

    private final CarService carService;

    @GetMapping("/{id}")
    public CarDTO findById(@PathVariable Long id){
        return carService.findById(id);
    }

    @GetMapping
    public List<CarDTO> findAll(){
        return carService.findAll();
    }

    @PostMapping
    public ResponseEntity<Void> save(@Validated @RequestBody CarDTO carDTO){
        carService.save(carDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping ("/edit")
    public ResponseEntity<Void> edit(@Validated @RequestBody CarDTO carDTO){
        try{
            carService.put(carDTO);
        }catch(NotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        carService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
