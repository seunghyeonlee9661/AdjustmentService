package com.sparta.dto;
import com.sparta.entity.Video;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class TopViewRequestDTO {
    @NotNull
    private Timestamp date;

    @NotNull
    private Video video;

    @NotNull
    private int ranking;

    @NotNull
    private Long views;

    public TopViewRequestDTO(Timestamp date, Video video,Long views) {
        this.date = date;
        this.video = video;
        this.views = views;
    }


}
