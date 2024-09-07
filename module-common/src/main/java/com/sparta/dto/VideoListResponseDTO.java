package com.sparta.dto;
import com.sparta.entity.Video;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

@Getter
public class VideoListResponseDTO {
    private Long id;
    private UserResponseDTO user;
    private String title;
    private String thumbnail;
    private Long viewCount;
    private String registrationDate;
    private Long duration;

    public VideoListResponseDTO(Video video){
        this.id = video.getId();
        this.user = new UserResponseDTO(video.getUser());
        this.thumbnail = video.getThumbnail();
        this.title = video.getTitle();
        this.viewCount = video.getViewCount();
        this.registrationDate = video.getRegistrationDate().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // 변경된 부분
        this.duration = video.getDuration();
    }
}