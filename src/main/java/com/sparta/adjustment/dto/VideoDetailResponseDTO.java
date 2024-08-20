//package com.sparta.adjustment.dto;
//
//import com.sparta.adjustment.entity.Video;
//import lombok.Getter;
//
//import java.sql.Timestamp;
//
//@Getter
//public class VideoDetailResponseDTO {
//    private Long id;
//    private UserResponseDTO user;
//    private String title;
//    private Long viewCount;
//    private Timestamp registrationDate;
//    private Long duration;
//
//    public VideoDetailResponseDTO(Video video){
//        this.id = video.getId();
//        this.user = new UserResponseDTO(video.getUser());
//        this.title = video.getTitle();
//        this.viewCount = video.getViewCount();
//        this.registrationDate = video.getRegistrationDate();
//        this.duration = video.getDuration();
//    }
//}
