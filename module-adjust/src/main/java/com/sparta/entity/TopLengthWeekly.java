package com.sparta.entity;

import com.sparta.entity.base.TopLengthBase;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor  // JPA는 기본 생성자가 필요합니다.
public class TopLengthWeekly extends TopLengthBase {
    public TopLengthWeekly(Timestamp date , Long length, Video video) {
        super(date, length, video);
    }
}
