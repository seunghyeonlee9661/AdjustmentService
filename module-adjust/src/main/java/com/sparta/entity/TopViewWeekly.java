package com.sparta.entity;

import com.sparta.entity.base.TopViewBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor  // JPA는 기본 생성자가 필요합니다.
public class TopViewWeekly extends TopViewBase {
    public TopViewWeekly(Timestamp date , Long views, Video video) {
        super(date, views, video);
    }
}
