package com.sparta.entity.base;

import com.sparta.entity.Video;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@MappedSuperclass  // 이 클래스를 다른 엔티티들이 상속받도록 함
@NoArgsConstructor
public class TopViewBase extends TopBase {

    @Column(nullable = false)
    private Long views;

    public TopViewBase(Timestamp date , Long views, Video video) {
        super(date, video);
        this.views = views;
    }
}