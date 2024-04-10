package com.wkrzyz.service.impl;

import com.wkrzyz.dto.CarDTO;
import com.wkrzyz.entity.BrandEntity;
import com.wkrzyz.entity.CarEntity;
import com.wkrzyz.exception.NotFoundException;
import com.wkrzyz.mapper.CarMapper;
import com.wkrzyz.repository.BrandEntityRepository;
import com.wkrzyz.repository.CarEntityRepository;
import com.wkrzyz.repository.OfferEntityRepository;
import com.wkrzyz.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarEntityRepository carEntityRepository;
    private final BrandEntityRepository brandEntityRepository;
    private final OfferEntityRepository offerEntityRepository;
    private final CarMapper carMapper;

    @Override
    public void save(CarDTO carDTO) {
        CarEntity carEntity = carMapper.fromCarDTOToCarEntity(carDTO);
        if(brandEntityRepository.findByName(carDTO.brand()).isEmpty()){
            BrandEntity brandEntity = new BrandEntity(carDTO.brand());
            brandEntityRepository.save(brandEntity);
            carEntity.setBrand(brandEntityRepository.findByName(carDTO.brand()).get());
        }else{
            BrandEntity brandEntity = brandEntityRepository.findByName(carDTO.brand()).get();
            carEntity.setBrand(brandEntity);
        }
        carEntityRepository.save(carEntity);
    }

    @Override
    public void put(CarDTO carDTO) throws NotFoundException {
        CarEntity carEntity = carMapper.fromCarDTOToCarEntity(carDTO);
        if(carEntityRepository.findById(carEntity.getId()).isPresent()){
            save(carDTO);
        }else{
            throw new NotFoundException("the object was already removed");
        }
    }

    @Override
    public List<CarDTO> findAll() {
        return carEntityRepository.findAll().stream()
                .map(carMapper::fromCarEntityToCarDTO)
                .toList();
    }

    @Override
    public CarDTO findById(Long id) {
        return carMapper.fromCarEntityToCarDTO(carEntityRepository
                .findById(id)
                .orElseThrow(()->new NotFoundException("Car not Found by id = " + id)));
    }

    @Override
    public CarEntity findObjById(Long id) {
       return carEntityRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {

        carEntityRepository.deleteById(id);
    }
}
