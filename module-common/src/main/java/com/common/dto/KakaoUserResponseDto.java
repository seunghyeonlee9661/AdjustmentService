package com.common.dto;

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
    private String nickname;
    private String email;
    private String image;

    public KakaoUserResponseDto(Long id, String nickname, String email, String image) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.image = image;
    }
}