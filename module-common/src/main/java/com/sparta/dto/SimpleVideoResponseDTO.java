package com.sparta.dto;

import com.sparta.entity.Video;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class SimpleVideoResponseDTO {
    private Long id;
    private String title;
    private String thumbnail;
    private Long duration;

    public SimpleVideoResponseDTO(Video video){
        this.id = video.getId();
        this.title = video.getTitle();
        this.thumbnail = video.getThumbnail();
        this.duration = video.getDuration();
    }
}
