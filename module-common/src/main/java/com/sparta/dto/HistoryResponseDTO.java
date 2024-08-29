package com.sparta.dto;
import com.sparta.entity.History;

import java.sql.Timestamp;

public class HistoryResponseDTO {
    private Long id;
    private VideoListResponseDTO video;
    private Long watchedDuration;
    private Timestamp watchedDate;

    public HistoryResponseDTO(History history){
        this.id = history.getId();
        this.video = new VideoListResponseDTO(history.getVideo());
        this.watchedDuration = history.getWatchedDuration();
        this.watchedDate = history.getWatchedDate();
    }
}
