package com.sparta.dto;
import com.sparta.entity.User;
import lombok.Getter;
@Getter
public class UserResponseDTO {
    private Long id;
    private String username;

    public UserResponseDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
    }
}