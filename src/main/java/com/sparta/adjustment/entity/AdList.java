//package com.sparta.adjustment.entity;
//
//import com.sparta.adjustment.entity.Ad;
//import com.sparta.adjustment.entity.Video;
//import jakarta.persistence.*;
//import lombok.Getter;
//
//@Entity
//@Getter
//@Table(name = "Ad_List")
//public class AdList {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "video_id", nullable = false)
//    private Video video;
//
//    @ManyToOne
//    @JoinColumn(name = "ad_id", nullable = false)
//    private Ad ad;
//
//    @Column(nullable = false)
//    private Long viewCount;
//
//    // Getters and Setters
//}