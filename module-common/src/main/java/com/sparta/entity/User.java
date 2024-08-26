package com.sparta.entity;

import com.sparta.dto.UserCreateRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import java.util.List;

@Entity
@Getter
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public void updateRole(){
        this.role = Role.ROLE_UPLODER;
    }
}