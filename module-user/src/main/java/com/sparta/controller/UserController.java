package com.sparta.controller;

import com.sparta.dto.*;
import com.sparta.security.UserDetailsImpl;
import com.sparta.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("")
public class UserController {
    private final UserService userService;

    /* 사용자 정보 반환 */
    @GetMapping("")
    public ResponseEntity<UserResponseDTO> findUser(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return ResponseEntity.ok(new UserResponseDTO(userDetails.getUser()));
    }

    /* 사용자 회원가입 */
    @PostMapping("")
    public ResponseEntity<String> createUser(@Valid @RequestBody UserCreateRequestDTO requestDTO) {
        return userService.createUser(requestDTO);
    }

    /* 사용자 회원탈퇴 */
    @DeleteMapping("")
    public ResponseEntity<String> removeUser(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse res) throws IOException {
        return userService.removeUser(userDetails,res);
    }

    /* 사용자 영상 목록 */
    @GetMapping("videos")
    public ResponseEntity<Page<VideoListResponseDTO>> userVideos(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam(value = "page", defaultValue = "0") int page) throws IOException {
        return userService.userVideos(userDetails,page);
    }

    /* 사용자 광고 목록 */
    @GetMapping("ads")
    public ResponseEntity<Page<AdResponseDTO>> userAds(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam(value = "page", defaultValue = "0") int page) throws IOException {
        return userService.userAds(userDetails,page);
    }

    /* 사용자 시청 기록 */
    @GetMapping("histories")
    public ResponseEntity<Page<HistoryResponseDTO>> userHistories(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam(value = "page", defaultValue = "0") int page) throws IOException {
        return userService.userHistories(userDetails,page);
    }
}