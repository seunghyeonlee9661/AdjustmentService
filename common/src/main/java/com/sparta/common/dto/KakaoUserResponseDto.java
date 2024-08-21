package com.sparta.common.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserResponseDto {
    private Long id;
    private String email;

    public KakaoUserResponseDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}