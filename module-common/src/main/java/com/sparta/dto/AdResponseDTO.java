package com.sparta.dto;

import com.sparta.entity.Ad;
import lombok.Getter;

@Getter
public class AdResponseDTO {
    private Long id;
    private UserResponseDTO user;
    private String title;

    public AdResponseDTO(Ad ad){
        this.id = ad.getId();
        this.user = new UserResponseDTO(ad.getUser());
        this.title = ad.getTitle();
    }
}