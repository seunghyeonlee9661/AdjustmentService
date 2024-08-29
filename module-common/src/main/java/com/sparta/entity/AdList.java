package com.sparta.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "ad_List")
@NoArgsConstructor
public class AdList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private Video video;

    @ManyToOne
    @JoinColumn(name = "ad_id", nullable = false)
    private Ad ad;

    @Column(nullable = false)
    private Long viewCount;

    public AdList(Video video,Ad ad){
        this.ad = ad;
        this.video = video;
        this.viewCount = 0L;
    }

    public void update(){
        this.viewCount++;
    }
}