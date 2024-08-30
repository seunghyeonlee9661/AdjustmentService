package com.sparta.dto;
import com.sparta.entity.base.TopLengthBase;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class TopLengthResponseDTO {
    private Long id;
    private String date;
    private SimpleVideoResponseDTO video;
    private int rank;
    private Long length;

    public TopLengthResponseDTO(TopLengthBase topLength){
        this.id = topLength.getId();
        this.date = topLength.getDate().toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // 변경된 부분
        this.video = new SimpleVideoResponseDTO(topLength.getVideo());
        this.rank = topLength.getRank();
        this.length = topLength.getLength();

    }
}
