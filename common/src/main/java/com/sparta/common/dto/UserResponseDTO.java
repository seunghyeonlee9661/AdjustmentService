package com.sparta.common.dto;
import com.sparta.common.entity.User;
import lombok.Getter;
@Getter
public class UserResponseDTO {
    private Long id;
    private String username;
    private Long kakaoId;

    public UserResponseDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.kakaoId = user.getKakaoId();
    }
}