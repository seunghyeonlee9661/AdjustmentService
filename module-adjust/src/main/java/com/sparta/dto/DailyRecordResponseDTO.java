package com.sparta.dto;

import com.sparta.entity.DailyRecord;
import lombok.Getter;
import java.time.format.DateTimeFormatter;

@Getter
public class DailyRecordResponseDTO {
    private Long id;
    private String date;
    private SimpleVideoResponseDTO video;
    private Long views;
    private Long length;
    private Long profite_video;
    private Long profite_ad;

    public DailyRecordResponseDTO(DailyRecord dailyRecord){
        this.id = dailyRecord.getId();
        this.date = dailyRecord.getDate().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // 변경된 부분
        this.video = new SimpleVideoResponseDTO(dailyRecord.getVideo());
        this.views = dailyRecord.getViews();
        this.length = dailyRecord.getLength();
        this.profite_video = dailyRecord.getProfit_video();
        this.profite_ad = dailyRecord.getProfit_ad();
    }
}
