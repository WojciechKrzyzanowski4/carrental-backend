package com.wkrzyz.mapper;

import com.wkrzyz.dto.UserDTO;
import com.wkrzyz.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {
    @Mapping(target="name", source="name")
    @Mapping(target="email", source="email")
    @Mapping(target="role", expression = "java(userEntity.getRole().name())")
    @Mapping(target="source", expression = "java(userEntity.getSource().name())")
    UserDTO fromUserEntityToUserDTO(UserEntity userEntity);

    @Mapping(target="name", source="name")
    @Mapping(target="email", source="email")
    @Mapping(target="role", ignore = true)
    @Mapping(target="source", ignore=true)
    UserEntity fromUserDTOTOUserEntity(UserDTO userDTO);
}
