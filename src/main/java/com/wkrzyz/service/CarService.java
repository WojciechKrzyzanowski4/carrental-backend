package com.wkrzyz.service;

import com.wkrzyz.dto.CarDTO;
import com.wkrzyz.entity.CarEntity;

import java.util.List;

public interface CarService {

    void save(CarDTO carDTO);

    void put(CarDTO carDTO);

    List<CarDTO> findAll();

    CarDTO findById(Long id);

    CarEntity findObjById(Long id);



    void delete(Long id);
}
