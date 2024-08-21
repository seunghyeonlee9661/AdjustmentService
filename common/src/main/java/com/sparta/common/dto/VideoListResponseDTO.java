package com.sparta.common.dto;
import com.sparta.common.entity.Video;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class VideoListResponseDTO {
    private Long id;
    private UserResponseDTO user;
    private String title;
    private Long viewCount;
    private Timestamp registrationDate;
    private Long duration;

    public VideoListResponseDTO(Video video){
        this.id = video.getId();
        this.user = new UserResponseDTO(video.getUser());
        this.title = video.getTitle();
        this.viewCount = video.getViewCount();
        this.registrationDate = video.getRegistrationDate();
        this.duration = video.getDuration();
    }
}