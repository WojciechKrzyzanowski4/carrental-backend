package com.wkrzyz.mapper;
import com.wkrzyz.dto.ReservationDTO;
import com.wkrzyz.entity.ReservationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ReservationMapper {

    @Mapping(target="id", source="id")
    @Mapping(target="reservationDate", source="reservationDate")
    ReservationDTO fromReservationEntityToReservationDTO(ReservationEntity reservationEntity);

    @Mapping(target="id", source="id")
    @Mapping(target="reservationDate",source="reservationDate")
    ReservationEntity fromReservationDTOTOReservationEntity(ReservationDTO reservationDTO);
}
