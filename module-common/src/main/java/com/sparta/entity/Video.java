package com.sparta.entity;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Table(name = "Video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

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

    @OneToMany(mappedBy = "video")
    private List<AdList> adLists;

    @OneToMany(mappedBy = "video")
    private List<History> histories;

    public void updateViews(){
        this.viewCount++;
    }
}