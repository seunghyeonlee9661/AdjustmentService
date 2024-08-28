package com.sparta.dto;
import com.sparta.entity.History;
import com.sparta.entity.Video;

import java.sql.Timestamp;

public class VideoDetailResponseDTO {
    private Long id;
    private UserResponseDTO user;
    private String title;
    private Long viewCount;
    private Timestamp registrationDate;
    private Long duration;
    private Long watchedDuration;

    public VideoDetailResponseDTO(Video video, Long watchedDuration){
        this.id = video.getId();
        this.user = new UserResponseDTO(video.getUser());
        this.title = video.getTitle();
        this.viewCount = video.getViewCount();
        this.registrationDate = video.getRegistrationDate();
        this.duration = video.getDuration();
        this.watchedDuration = watchedDuration;
    }
}
