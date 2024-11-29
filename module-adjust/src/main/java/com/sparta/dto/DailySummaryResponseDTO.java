package com.sparta.dto;

import com.sparta.entity.DailyRecord;
import com.sparta.entity.DailySummary;
import com.sparta.entity.Video;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class DailySummaryResponseDTO {
    private final Long id;
    private final String date;
    private final SimpleVideoResponseDTO video;
    
    // 일간 주간 월간 영상 시청수
    private Long videoDailyViewCount;
    private Long videoWeeklyViewCount;
    private Long videoMonthlyViewCount;
    
    // 일간 주간 월간 영상 수익
    private Long videoDailyProfit;
    private Long videoWeeklyProfit;
    private Long videoMonthlyProfit;

    // 일간 주간 월간 광고 시청수
    private Long adDailyViewCount;
    private Long adWeeklyViewCount;
    private Long adMonthlyViewCount;

    // 일간 주간 월간 광고 수익
    private Long adDailyProfit;
    private Long adWeeklyProfit;
    private Long adMonthlyProfit;

    public DailySummaryResponseDTO(DailySummary dailySummary){
        this.id = dailySummary.getId();
        this.date = dailySummary.getDate().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // 변경된 부분
        this.video = new SimpleVideoResponseDTO(dailySummary.getVideo());
        this.videoDailyViewCount = dailySummary.getVideoDailyViewCount();
        this.videoWeeklyViewCount = dailySummary.getVideoWeeklyViewCount();
        this.videoMonthlyViewCount = dailySummary.getVideoMonthlyViewCount();
        this.videoDailyProfit = dailySummary.getVideoDailyProfit();
        this.videoWeeklyProfit = dailySummary.getVideoWeeklyProfit();
        this.videoMonthlyProfit = dailySummary.getVideoMonthlyProfit();
        this.adDailyViewCount = dailySummary.getAdDailyViewCount();
        this.adWeeklyViewCount = dailySummary.getAdWeeklyViewCount();
        this.adMonthlyViewCount = dailySummary.getAdMonthlyViewCount();
        this.adDailyProfit = dailySummary.getAdDailyProfit();
        this.adWeeklyProfit = dailySummary.getAdWeeklyProfit();
        this.adMonthlyProfit = dailySummary.getAdMonthlyProfit();
    }

    public DailySummaryResponseDTO(LocalDateTime date, Video video,
                                   Long videoDailyViewCount, Long videoWeeklyViewCount,Long videoMonthlyViewCount,
                                   Long videoDailyProfit, Long videoWeeklyProfit,Long videoMonthlyProfit,
                                   Long adDailyViewCount, Long adWeeklyViewCount,Long adMonthlyViewCount,
                                   Long adDailyProfit, Long adWeeklyProfit,Long adMonthlyProfit){
        this.id = null;
        this.date = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // 변경된 부분
        this.video = new SimpleVideoResponseDTO(video);
        this.videoDailyViewCount = videoDailyViewCount;
        this.videoWeeklyViewCount = videoWeeklyViewCount;
        this.videoMonthlyViewCount = videoMonthlyViewCount;
        this.videoDailyProfit = videoDailyProfit;
        this.videoWeeklyProfit = videoWeeklyProfit;
        this.videoMonthlyProfit = videoMonthlyProfit;
        this.adDailyViewCount = adDailyViewCount;
        this.adWeeklyViewCount = adWeeklyViewCount;
        this.adMonthlyViewCount = adMonthlyViewCount;
        this.adDailyProfit = adDailyProfit;
        this.adWeeklyProfit = adWeeklyProfit;
        this.adMonthlyProfit = adMonthlyProfit;
    }
}
