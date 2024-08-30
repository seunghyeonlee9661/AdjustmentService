package com.sparta.entity.base;

import com.sparta.dto.TopViewRequestDTO;
import com.sparta.entity.Video;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class TopViewBase extends TopBase {

    @Column(nullable = false)
    private Long views;

    public TopViewBase(Timestamp date , Long views, Video video) {
        super(date, video);
        this.views = views;
    }
}