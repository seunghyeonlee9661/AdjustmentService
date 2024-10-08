package com.sparta.service;

import com.sparta.dto.AdResponseDTO;
import com.sparta.dto.HistoryResponseDTO;
import com.sparta.dto.UserCreateRequestDTO;
import com.sparta.dto.VideoListResponseDTO;
import com.sparta.entity.Ad;
import com.sparta.entity.History;
import com.sparta.entity.User;
import com.sparta.entity.Video;
import com.sparta.repository.AdRepository;
import com.sparta.repository.HistoryRepository;
import com.sparta.repository.UserRepository;
import com.sparta.repository.VideoRepository;
import com.sparta.security.JwtUtil;
import com.sparta.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;
    private final HistoryRepository historyRepository;

    private final AdRepository adRepository;


    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    /* 회원가입 */
    @Transactional
    public ResponseEntity<String> createUser(UserCreateRequestDTO userCreateRequestDTO){
        if (userRepository.findByUsername(userCreateRequestDTO.getUsername()).isPresent()) throw new IllegalArgumentException("중복된 Email 입니다.");
        if (!userCreateRequestDTO.getPassword().equals(userCreateRequestDTO.getPasswordCheck())) throw new IllegalArgumentException("비밀번호 확인이 일치하지 않습니다.");
        User user = new User(userCreateRequestDTO,passwordEncoder.encode(userCreateRequestDTO.getPassword()));
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    /* 회원 탈퇴*/
    @Transactional
    public ResponseEntity<String> removeUser(UserDetailsImpl userDetails, HttpServletResponse res) throws IOException {
        User user = userDetails.getUser();
        userRepository.delete(user);
        jwtUtil.removeJwtCookie(res);
        return ResponseEntity.ok().body("User deleted successfully");
    }

    /* 회원 탈퇴*/
    public ResponseEntity<Page<VideoListResponseDTO>> userVideos(UserDetailsImpl userDetails, int page) throws IOException {
        User user = userDetails.getUser();
        Pageable pageable = PageRequest.of(page, 8);
        Page<Video> videoPage = videoRepository.findByUserId(user.getId(),pageable);
        return ResponseEntity.ok().body(videoPage.map(VideoListResponseDTO::new));
    }

    /* 회원 탈퇴*/
    public ResponseEntity<Page<AdResponseDTO>> userAds(UserDetailsImpl userDetails, int page) throws IOException {
        User user = userDetails.getUser();
        Pageable pageable = PageRequest.of(page, 8);
        Page<Ad> AdPages = adRepository.findByUserId(user.getId(),pageable);
        return ResponseEntity.ok().body(AdPages.map(AdResponseDTO::new));
    }

    /* 회원 탈퇴*/
    public ResponseEntity<Page<HistoryResponseDTO>> userHistories(UserDetailsImpl userDetails, int page) throws IOException {
        User user = userDetails.getUser();
        Pageable pageable = PageRequest.of(page, 8);
        Page<History> historyPage = historyRepository.findByUserId(user.getId(),pageable);
        return ResponseEntity.ok().body(historyPage.map(HistoryResponseDTO::new));
    }
}