package com.sparta.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AdCreateRequestDTO {

    @NotBlank(message = "영상 업로드는 필수입니다.")
    private String url;

    @NotBlank(message = "제목은 필수입니다.")
    private String title;
}