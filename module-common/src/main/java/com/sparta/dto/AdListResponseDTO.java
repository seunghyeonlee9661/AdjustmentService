package com.sparta.dto;

import com.sparta.entity.Ad;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class AdListResponseDTO {
    private Long id;
    private UserResponseDTO user;
    private String title;
    private Long viewCount;
    private Timestamp registrationDate;
    private Long duration;

    public AdListResponseDTO(Ad ad){
        this.id = ad.getId();
        this.user = new UserResponseDTO(ad.getUser());
        this.title = ad.getTitle();
        this.viewCount = ad.getViewCount();
    }
}