package com.sparta.dto;

import com.sparta.entity.Ad;
import com.sparta.entity.User;
import com.sparta.entity.Video;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
public class SimpleAdResponseDTO {
    private Long id;
    private String url;
    private String title;

    public SimpleAdResponseDTO(Ad ad){
        this.id = ad.getId();
        this.url = ad.getUrl();
        this.title = ad.getTitle();
    }
}
