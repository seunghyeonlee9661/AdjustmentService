package com.sparta.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "history")
@NoArgsConstructor
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private Video video;

    @Column(nullable = false)
    private Long watchedDuration;

    @Column(nullable = false)
    private Long watchedLength;

    @Column(nullable = false)
    private Timestamp watchedDate;

    public History(User user, Video video){
        this.user = user;
        this.video = video;
        watchedDuration = 0L;
        watchedLength = 0L;
        this.watchedDate = Timestamp.valueOf(LocalDateTime.now()); // 현재 시간으로 설정
    }

    public void update(Long watchedDuration, Long watchedLength){
        this.watchedDate = Timestamp.valueOf(LocalDateTime.now());
        this.watchedDuration = watchedDuration;
        this.watchedLength += watchedLength;
    }
}