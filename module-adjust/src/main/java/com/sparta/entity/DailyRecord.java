package com.sparta.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@Entity
@NoArgsConstructor
public class DailyRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private Video video;

    @Column(nullable = false)
    private Long totalVideoViews;

    @Column(nullable = false)
    private Long totalAdViews;

    public DailyRecord(Timestamp date, Video video, Long totalVideoViews,Long totalAdViews){
        this.date = date;
        this.video = video;
        this.totalVideoViews = totalVideoViews;
        this.totalAdViews = totalAdViews;
    }

    public void update(Long totalVideoViews,Long totalAdViews){
        this.totalVideoViews = totalVideoViews;
        this.totalAdViews = totalAdViews;
    }
}
