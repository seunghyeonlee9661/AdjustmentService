package com.sparta.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class TopLengthRequestDTO {
    @NotNull
    private Timestamp date;

    @NotNull
    private Long video_id;

    @NotNull
    private int ranking;

    @NotNull
    private Long length;
}
