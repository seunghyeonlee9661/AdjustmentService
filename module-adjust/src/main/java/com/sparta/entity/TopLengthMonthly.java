package com.sparta.entity;

import com.sparta.dto.TopLengthRequestDTO;
import com.sparta.entity.base.TopLengthBase;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor  // JPA는 기본 생성자가 필요합니다.
public class TopLengthMonthly extends TopLengthBase {

    public TopLengthMonthly(Timestamp date , Long length, Video video) {
        super(date, length, video);
    }
}
