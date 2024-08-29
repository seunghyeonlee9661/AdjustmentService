package com.sparta.dto;

import com.sparta.entity.AdList;
import lombok.Getter;

@Getter
public class AdListResponseDTO {
    private Long id;
    private AdResponseDTO ad;
    private Long viewCount;

    public AdListResponseDTO(AdList adList) {
        this.id = adList.getId();
        this.ad = new AdResponseDTO(adList.getAd());  // 광고 정보 포함
        this.viewCount = adList.getViewCount();
    }
}