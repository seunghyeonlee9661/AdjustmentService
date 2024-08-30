package com.sparta.entity.base;

import com.sparta.entity.Video;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class TopLengthBase extends TopBase {

    @Column(nullable = false)
    private Long length;

    public TopLengthBase(Timestamp date , Long length, Video video) {
        super(date, video);
        this.length = length;
    }
}