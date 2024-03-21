package com.wkrzyz.mapper;

import com.wkrzyz.dto.OfferDTO;
import com.wkrzyz.entity.CarEntity;
import com.wkrzyz.entity.OfferEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-21T17:14:36+0100",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.6.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class OfferMapperImpl implements OfferMapper {

    @Override
    public OfferDTO fromOfferEntityToOfferDTO(OfferEntity offerEntity) {
        if ( offerEntity == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;
        Double price = null;
        String model = null;
        String year = null;

        id = offerEntity.getId();
        name = offerEntity.getName();
        description = offerEntity.getDescription();
        price = offerEntity.getPrice();
        model = offerEntityCarModel( offerEntity );
        Integer yearOfManufacture = offerEntityCarYearOfManufacture( offerEntity );
        if ( yearOfManufacture != null ) {
            year = String.valueOf( yearOfManufacture );
        }

        String brand = offerEntity.getCar().getBrand().getName();

        OfferDTO offerDTO = new OfferDTO( id, name, description, price, model, brand, year );

        return offerDTO;
    }

    @Override
    public OfferEntity fromOfferDTOToOfferEntity(OfferDTO offerDTO) {
        if ( offerDTO == null ) {
            return null;
        }

        OfferEntity offerEntity = new OfferEntity();

        offerEntity.setId( offerDTO.id() );
        offerEntity.setName( offerDTO.name() );
        offerEntity.setDescription( offerDTO.description() );
        offerEntity.setPrice( offerDTO.price() );

        return offerEntity;
    }

    private String offerEntityCarModel(OfferEntity offerEntity) {
        if ( offerEntity == null ) {
            return null;
        }
        CarEntity car = offerEntity.getCar();
        if ( car == null ) {
            return null;
        }
        String model = car.getModel();
        if ( model == null ) {
            return null;
        }
        return model;
    }

    private Integer offerEntityCarYearOfManufacture(OfferEntity offerEntity) {
        if ( offerEntity == null ) {
            return null;
        }
        CarEntity car = offerEntity.getCar();
        if ( car == null ) {
            return null;
        }
        Integer yearOfManufacture = car.getYearOfManufacture();
        if ( yearOfManufacture == null ) {
            return null;
        }
        return yearOfManufacture;
    }
}
