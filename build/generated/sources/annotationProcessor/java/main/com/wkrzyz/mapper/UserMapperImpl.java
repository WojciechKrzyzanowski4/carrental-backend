package com.wkrzyz.mapper;

import com.wkrzyz.dto.UserDTO;
import com.wkrzyz.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-21T17:14:36+0100",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.6.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO fromUserEntityToUserDTO(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        String name = null;
        String email = null;

        name = userEntity.getName();
        email = userEntity.getEmail();

        String role = userEntity.getRole().name();
        String source = userEntity.getSource().name();

        UserDTO userDTO = new UserDTO( name, email, role, source );

        return userDTO;
    }

    @Override
    public UserEntity fromUserDTOTOUserEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setName( userDTO.name() );
        userEntity.setEmail( userDTO.email() );

        return userEntity;
    }
}
