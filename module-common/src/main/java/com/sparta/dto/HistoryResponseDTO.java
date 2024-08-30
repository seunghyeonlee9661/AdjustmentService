package com.sparta.dto;
import com.sparta.entity.History;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

@Getter
public class HistoryResponseDTO {
    private Long id;
    private SimpleVideoResponseDTO video;
    private Long watchedDuration;
    private String watchedDate;

    public HistoryResponseDTO(History history){
        this.id = history.getId();
        this.video = new SimpleVideoResponseDTO(history.getVideo());
        this.watchedDuration = history.getWatchedDuration();
        this.watchedDate = history.getWatchedDate().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // 변경된 부분

    }
}
