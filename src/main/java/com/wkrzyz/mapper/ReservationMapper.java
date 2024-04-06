package com.wkrzyz.mapper;
import com.wkrzyz.dto.ReservationDTO;
import com.wkrzyz.entity.ReservationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ReservationMapper {

    @Mapping(target="id", source="id")
    @Mapping(target="reservationDate", source="reservationDate")
    @Mapping(target="user", expression="java(userMapper.fromUserEntityToUserDTO(reservationEntity.getUser()))")
    @Mapping(target="offer", expression="java(offerMapper.fromOfferEntityToOfferDTO(reservationEntity.getOffer()))")
    ReservationDTO fromReservationEntityToReservationDTO(ReservationEntity reservationEntity);

    @Mapping(target="id", source="id")
    @Mapping(target="reservationDate",source="reservationDate")
    @Mapping(target="offer",ignore = true)
    @Mapping(target="user",ignore = true)
    ReservationEntity fromReservationDTOTOReservationEntity(ReservationDTO reservationDTO);


    UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    OfferMapper offerMapper = Mappers.getMapper(OfferMapper.class);
}
