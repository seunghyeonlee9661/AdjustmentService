package com.sparta.entity;

import com.sparta.dto.UserCreateRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // 카카오 아이디
    @Column(name = "kakao_id", nullable = true)
    private Long kakaoId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Ad> ads;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Video> videos;

    @OneToMany(mappedBy = "user")
    private List<History> histories;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    public User(UserCreateRequestDTO requestDTO, String password){
        this.username = requestDTO.getUsername();
        this.password = password;
        this.role = Role.ROLE_USER;
    }

    /* 카카오 유저 생성*/
    public User(String password, Long kakaoId,String username){
        this.username = username;
        this.kakaoId = kakaoId;
        this.password = password;
        this.role = Role.ROLE_USER;
    }

    /* 카카오 아이디 업데이트 */
    public User kakaoIdUpdate(Long kakaoId) {
        this.kakaoId = kakaoId;
        return this;
    }

    public void updateRole(){
        this.role = Role.ROLE_UPLODER;
    }
}