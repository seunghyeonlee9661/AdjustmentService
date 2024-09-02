package com.sparta.entity;
import com.sparta.dto.VideoCreateRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Table(name = "video")
@NoArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    @Column(nullable = true)
    private String thumbnail;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Long viewCount;

    @Column(nullable = false)
    private Timestamp registrationDate;

    @Column(nullable = false)
    private Long duration;

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL)
    private List<AdList> adLists;

    @OneToMany(mappedBy = "video")
    private List<History> histories;

    public Video (VideoCreateRequestDTO requestDTO,User user){
        this.url = requestDTO.getUrl();
        this.thumbnail = requestDTO.getThumbnail();
        this.title = requestDTO.getTitle();
        this.viewCount = 0L;
        this.registrationDate = Timestamp.valueOf(LocalDateTime.now()); // 현재 시간으로 설정
        this.duration = requestDTO.getDuration();
        this.user = user;
    }

    public void updateViews(){
        this.viewCount++;
    }

    public Long getProfit() {
        Long views = this.viewCount;  // 조회수 가져오기
        double profit = 0.0;  // 수익 변수 초기화

        // 조회수 구간에 따라 수익 계산
        if (views > 1000000) {
            profit += (views - 1000000) * 1.5;
            views = 1000000L;
        }
        if (views > 500000) {
            profit += (views - 500000) * 1.3;
            views = 500000L;
        }
        if (views > 100000) {
            profit += (views - 100000) * 1.1;
            views = 100000L;
        }
        profit += views * 1.0;  // 나머지 조회수에 대한 수익 계산

        // double을 long으로 변환 (절사)
        return (long) profit;
    }
}