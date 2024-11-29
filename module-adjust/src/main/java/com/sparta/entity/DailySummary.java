package com.sparta.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class DailySummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Timestamp date;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private Video video;

    @Column(nullable = false)
    private Long videoDailyViewCount;

    @Column(nullable = false)
    private Long videoWeeklyViewCount;

    @Column(nullable = false)
    private Long videoMonthlyViewCount;

    @Column(nullable = false)
    private Long videoDailyProfit;

    @Column(nullable = false)
    private Long videoWeeklyProfit;

    @Column(nullable = false)
    private Long videoMonthlyProfit;

    @Column(nullable = false)
    private Long adDailyViewCount;

    @Column(nullable = false)
    private Long adWeeklyViewCount;

    @Column(nullable = false)
    private Long adMonthlyViewCount;

    @Column(nullable = false)
    private Long adDailyProfit;

    @Column(nullable = false)
    private Long adWeeklyProfit;

    @Column(nullable = false)
    private Long adMonthlyProfit;

    public DailySummary(Timestamp date, Video video,
                        Long videoDailyViewCount,Long videoWeeklyViewCount,Long videoMonthlyViewCount,
                        Long adDailyViewCount,Long adWeeklyViewCount,Long adMonthlyViewCount){
        this.date = date;
        this.video = video;
        this.videoDailyViewCount = videoDailyViewCount;
        this.videoDailyProfit = getVideoProfit(this.videoDailyViewCount);
        this.videoWeeklyViewCount = videoWeeklyViewCount;
        this.videoWeeklyProfit = getVideoProfit(this.videoWeeklyViewCount);
        this.videoMonthlyViewCount = videoMonthlyViewCount;
        this.videoMonthlyProfit = getVideoProfit(this.videoMonthlyViewCount);
        this.adDailyViewCount = adDailyViewCount;
        this.adDailyProfit = getAdProfit(this.adDailyViewCount);
        this.adWeeklyViewCount = adWeeklyViewCount;
        this.adWeeklyProfit = getAdProfit(this.adWeeklyViewCount);
        this.adMonthlyViewCount = adMonthlyViewCount;
        this.adMonthlyProfit = getAdProfit(this.adMonthlyViewCount);
    }

    public Long getVideoProfit(Long viewCount) {
        double profit = 0.0;  // 수익 변수 초기화
        // 조회수 구간에 따라 수익 계산
        if (viewCount > 1000000) {
            profit += (viewCount - 1000000) * 1.5;
            viewCount = 1000000L;
        }
        if (viewCount > 500000) {
            profit += (viewCount - 500000) * 1.3;
            viewCount = 500000L;
        }
        if (viewCount > 100000) {
            profit += (viewCount - 100000) * 1.1;
            viewCount = 100000L;
        }
        profit += viewCount * 1.0;  // 나머지 조회수에 대한 수익 계산
        // double을 long으로 변환 (절사)
        return (long) profit;
    }

    public Long getAdProfit(Long viewCount) {
        long profit = 0L;  // 수익 변수 초기화
        // 조회수 구간에 따라 수익 계산
        if (viewCount > 1000000) {
            profit += (viewCount - 1000000) * 20;
            viewCount = 1000000L;
        }
        if (viewCount > 500000) {
            profit += (viewCount - 500000) * 15;
            viewCount = 500000L;
        }
        if (viewCount > 100000) {
            profit += (viewCount - 100000) * 112;
            viewCount = 100000L;
        }
        profit += viewCount * 10;  // 나머지 조회수에 대한 수익 계산
        // double을 long으로 변환 (절사)
        return profit;
    }
}
