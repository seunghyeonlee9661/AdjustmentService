package com.sparta.adjustment.DTO;

import com.sparta.adjustment.entity.User;
import lombok.Getter;

import java.text.SimpleDateFormat;

@Getter
public class UserResponseDTO {
    private Long id;
    private String email;
    private String nickname;
    private String image;
    private String birthday;

    public UserResponseDTO(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.image = user.getImage();
        this.birthday = (user.getBirthday() != null) ? new SimpleDateFormat("yyyy MM dd").format(user.getBirthday()) : null;
    }
}
