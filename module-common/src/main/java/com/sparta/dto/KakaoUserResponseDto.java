package com.sparta.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/*
작성자 : 이승현
카카오 유저 반환 DTO
 */
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