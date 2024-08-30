package com.sparta.dto;
import com.sparta.entity.base.TopViewBase;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class TopViewResponseDTO {
    private Long id;
    private String date;
    private SimpleVideoResponseDTO video;
    private int rank;
    private Long views;

    public TopViewResponseDTO(TopViewBase topView){
        this.id = topView.getId();
        this.date = topView.getDate().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // 변경된 부분
        this.video = new SimpleVideoResponseDTO(topView.getVideo());
        this.rank = topView.getRank();
        this.views = topView.getViews();

    }
}
