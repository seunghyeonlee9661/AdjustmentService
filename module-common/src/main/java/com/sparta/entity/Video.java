package com.sparta.entity;
import com.sparta.dto.VideoCreateRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Long getTotalAdViewCount() {
        return adLists.stream().mapToLong(AdList::getViewCount).sum();
    }
}