package com.wkrzyz.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactDTO {

    private String email;
    private String subject;
    private String message;

}
