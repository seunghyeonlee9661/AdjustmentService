package com.sparta.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class VideoPauseRequestDTO {

    @NotNull
    private Long watchedDuration;

    @NotNull
    private Long watchedLength;
}
