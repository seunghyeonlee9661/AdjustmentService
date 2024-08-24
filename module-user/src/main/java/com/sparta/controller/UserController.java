package com.sparta.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.dto.UserCreateRequestDTO;
import com.sparta.dto.UserResponseDTO;
import com.sparta.security.UserDetailsImpl;
import com.sparta.service.KakaoLoginService;
import com.sparta.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("")
public class UserController {
    private final UserService userService;
    private final KakaoLoginService kakaoLoginService;

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
    /* 사용자 정보 추가 */
    @PostMapping("/kakao/callback")
    public ResponseEntity<String> KakaoLogin(@RequestParam String code, HttpServletResponse response)  throws JsonProcessingException {
        return kakaoLoginService.kakaoLogin(code,response);
    }
}