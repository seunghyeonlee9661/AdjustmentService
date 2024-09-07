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
    private Long views;

    @Column(nullable = false)
    private Long length;

    @Column(nullable = false)
    private Long profit_video;

    @Column(nullable = false)
    private Long profit_ad;

    public DailyRecord(Timestamp date, Video video, Long views,Long length, Long profit_video,Long profit_ad){
        this.date = date;
        this.video = video;
        this.views = views;
        this.length = length;
        this.profit_video = profit_video;
        this.profit_ad = profit_ad;
    }

    public void update(Long views,Long length, Long profit_video,Long profit_ad){
        this.views = views;
        this.length = length;
        this.profit_video = profit_video;
        this.profit_ad = profit_ad;
    }
}
