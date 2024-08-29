package com.sparta.dto;
import com.sparta.entity.Video;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class VideoDetailResponseDTO {
    private Long id;
    private UserResponseDTO user;
    private String title;
    private Long viewCount;
    private Timestamp registrationDate;
    private Long duration;
    private Long watchedDuration;
    private List<AdListResponseDTO> adList;

    public VideoDetailResponseDTO(Video video, Long watchedDuration){
        this.id = video.getId();
        this.user = new UserResponseDTO(video.getUser());
        this.title = video.getTitle();
        this.viewCount = video.getViewCount();
        this.registrationDate = video.getRegistrationDate();
        this.duration = video.getDuration();
        this.watchedDuration = watchedDuration;
        this.adList = video.getAdLists().stream().map(AdListResponseDTO::new).collect(Collectors.toList());
    }
}
