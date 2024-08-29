package com.sparta.entity;
import com.sparta.dto.AdCreateRequestDTO;
import com.sparta.dto.VideoCreateRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Table(name = "ad")
@NoArgsConstructor
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "ad")
    private List<AdList> adLists;

    public Ad (AdCreateRequestDTO requestDTO, User user){
        this.url = requestDTO.getUrl();
        this.title = requestDTO.getTitle();
        this.user = user;
    }

}