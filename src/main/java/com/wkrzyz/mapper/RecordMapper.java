package com.wkrzyz.mapper;

import com.wkrzyz.dto.RecordDTO;
import com.wkrzyz.entity.RecordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RecordMapper {

    @Mapping(target="id", source="id")
    @Mapping(target="recordDate", source="recordDate")
    @Mapping(target="status",source="status")
    @Mapping(target="user", expression="java(userMapper.fromUserEntityToUserDTO(recordEntity.getUser()))")
    @Mapping(target="offer", expression="java(offerMapper.fromOfferEntityToOfferDTO(recordEntity.getOffer()))")
    RecordDTO fromRecordEntityToRecordDTO(RecordEntity recordEntity);

    @Mapping(target="id", source="id")
    @Mapping(target="recordDate",source="recordDate")
    @Mapping(target="status",source="status")
    @Mapping(target="offer",ignore = true)
    @Mapping(target="user",ignore = true)
    RecordEntity fromRecordDTOToRecordEntity(RecordDTO recordDTO);


    UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    OfferMapper offerMapper = Mappers.getMapper(OfferMapper.class);
}
