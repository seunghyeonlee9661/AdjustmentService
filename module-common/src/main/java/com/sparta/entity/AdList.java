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

    public Long getProfit() {
        Long views = this.viewCount;  // 조회수 가져오기
        long profit = 0L;  // 수익 변수 초기화

        // 조회수 구간에 따라 수익 계산
        if (views > 1000000) {
            profit += (views - 1000000) * 20;
            views = 1000000L;
        }
        if (views > 500000) {
            profit += (views - 500000) * 15;
            views = 500000L;
        }
        if (views > 100000) {
            profit += (views - 100000) * 112;
            views = 100000L;
        }
        profit += views * 10;  // 나머지 조회수에 대한 수익 계산

        // double을 long으로 변환 (절사)
        return profit;
    }
}