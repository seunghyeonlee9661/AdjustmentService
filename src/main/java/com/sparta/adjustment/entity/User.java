package com.sparta.adjustment.entity;

import com.sparta.adjustment.dto.UserCreateRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private Long kakaoId;

    @Column(nullable = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Ad> ads;

    @OneToMany(mappedBy = "user")
    private List<Video> videos;

    @OneToMany(mappedBy = "user")
    private List<History> histories;

    public User(UserCreateRequestDTO requestDTO, String password){
        this.username = requestDTO.getUsername();
        this.password = password;
        this.role = Role.ROLE_USER;
    }

    /* 카카오 유저 생성*/
    public User(String email,String password, Long kakaoId){
        this.kakaoId = kakaoId;
        this.username = email;
        this.password = password;
    }

    public void updateRole(){
        this.role = Role.ROLE_UPLODER;
    }
}









