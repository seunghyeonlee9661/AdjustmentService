package com.sparta.dto;

import com.sparta.entity.DailyRecord;
import lombok.Getter;
import java.time.format.DateTimeFormatter;

@Getter
public class DailyRecordResponseDTO {
    private final Long id;
    private final String date;
    private final SimpleVideoResponseDTO video;
    private final Long totalVideoViews;
    private final Long totalAdViews;

    public DailyRecordResponseDTO(DailyRecord dailyRecord){
        this.id = dailyRecord.getId();
        this.date = dailyRecord.getDate().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // 변경된 부분
        this.video = new SimpleVideoResponseDTO(dailyRecord.getVideo());
        this.totalVideoViews = dailyRecord.getTotalVideoViews();
        this.totalAdViews = dailyRecord.getTotalAdViews();
    }
}
