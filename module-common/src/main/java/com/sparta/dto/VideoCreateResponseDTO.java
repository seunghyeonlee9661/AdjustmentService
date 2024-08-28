package com.sparta.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VideoCreateResponseDTO {
    private String url;
    private String thumbnail;
    private Long duration;

    public VideoCreateResponseDTO(String url,String thumbnail, Long duration){
        this.url = url;
        this.thumbnail = thumbnail;
        this.duration = duration;
    }
}