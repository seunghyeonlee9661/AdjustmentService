package com.sparta.dto;

import com.sparta.entity.DailyRecord;
import com.sparta.entity.Video;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

@Getter
public class DailyRecordResponseDTO {
    private Long id;
    private String date;
    private SimpleVideoResponseDTO video;
    private Long views;
    private Long length;

    public DailyRecordResponseDTO(DailyRecord dailyRecord){
        this.id = dailyRecord.getId();
        this.date = dailyRecord.getDate().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // 변경된 부분
        this.video = new SimpleVideoResponseDTO(dailyRecord.getVideo());
        this.views = dailyRecord.getViews();
        this.length = dailyRecord.getLength();
    }
}
