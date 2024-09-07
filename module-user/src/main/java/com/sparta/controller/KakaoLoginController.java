package com.sparta.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.service.KakaoLoginService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/kakao")
public class KakaoLoginController {
    private final KakaoLoginService kakaoLoginService;

    /* 카카오 로그인 콜백 처리 */
    @PostMapping("/login")
    public ResponseEntity<String> kakaoLogin(@RequestBody Map<String, String> payload, HttpServletResponse response) throws JsonProcessingException {
        String code = payload.get("code");
        String redirectUri = payload.get("redirectUri");
        return kakaoLoginService.kakaoLogin(code,redirectUri, response);
    }
}
