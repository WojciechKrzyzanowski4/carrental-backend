package com.wkrzyz.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeedbackDTO {

    private String email;
    private String overview;
    private String description;
    private String type;

}
