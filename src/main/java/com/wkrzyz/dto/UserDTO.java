package com.wkrzyz.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserDTO(Long id,
        @NotEmpty(message="a user should have a name")String name,
        @NotEmpty(message="a user should have an email")String email,
        @NotEmpty(message="a user should have roles")String role,
        @NotEmpty(message="a user should be logged in from a particular source")String source

) {


}
