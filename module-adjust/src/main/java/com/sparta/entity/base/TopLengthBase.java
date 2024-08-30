package com.sparta.entity.base;

import com.sparta.dto.TopLengthRequestDTO;
import com.sparta.entity.Video;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Entity
public class TopLengthBase extends TopBase {

    @Column(nullable = false)
    private Long length;

    public TopLengthBase(Timestamp date , Long length, Video video) {
        super(date, video);
        this.length = length;
    }
}