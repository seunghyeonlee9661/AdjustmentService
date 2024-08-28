package com.sparta.dto;
import com.sparta.entity.Video;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class VideoListResponseDTO {
    private Long id;
    private UserResponseDTO user;
    private String title;
    private String thumbnail;
    private Long viewCount;
    private Timestamp registrationDate;
    private Long duration;

    public VideoListResponseDTO(Video video){
        this.id = video.getId();
        this.user = new UserResponseDTO(video.getUser());
        this.thumbnail = video.getThumbnail();
        this.title = video.getTitle();
        this.viewCount = video.getViewCount();
        this.registrationDate = video.getRegistrationDate();
        this.duration = video.getDuration();
    }
}