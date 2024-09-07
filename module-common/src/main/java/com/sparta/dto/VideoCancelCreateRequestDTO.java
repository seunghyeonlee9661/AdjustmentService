package com.sparta.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class VideoCancelCreateRequestDTO {
    @NotBlank
    private String url;

    @NotBlank
    private String thumbnail;
}
