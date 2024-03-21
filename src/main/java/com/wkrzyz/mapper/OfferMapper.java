package com.wkrzyz.mapper;

import com.wkrzyz.dto.OfferDTO;
import com.wkrzyz.entity.OfferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface OfferMapper {
    @Mapping(target="id", source="id")
    @Mapping(target="name", source="name")
    @Mapping(target="description", source="description")
    @Mapping(target="price", source="price")
    @Mapping(target="model", source="car.model")
    @Mapping(target="year", source="car.yearOfManufacture")
    @Mapping(target="brand", expression="java(offerEntity.getCar().getBrand().getName())")
    OfferDTO fromOfferEntityToOfferDTO(OfferEntity offerEntity);

    @Mapping(target="id", source="id")
    @Mapping(target="name", source="name")
    @Mapping(target="description", source="description")
    @Mapping(target="price", source="price")
    OfferEntity fromOfferDTOToOfferEntity(OfferDTO offerDTO);
}
