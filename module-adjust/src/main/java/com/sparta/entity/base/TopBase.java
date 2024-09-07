package com.sparta.entity.base;

import com.sparta.entity.Video;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@MappedSuperclass  // 이 클래스를 다른 엔티티들이 상속받도록 함
@NoArgsConstructor  // JPA는 기본 생성자가 필요합니다.
public abstract class TopBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Timestamp date;

    @Setter
    @Column(nullable = false, name = "ranking") // 수정된 필드명
    private int ranking;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private Video video;

    public TopBase(Timestamp date, Video video) {
        this.date = date;
        this.video = video;
    }
}
