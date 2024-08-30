package com.sparta.entity;

import com.sparta.entity.base.TopViewBase;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@Entity
@NoArgsConstructor  // JPA는 기본 생성자가 필요합니다.
public class TopViewMonthly extends TopViewBase {

    public TopViewMonthly(Timestamp date , Long views, Video video) {
        super(date, views, video);
    }
}
