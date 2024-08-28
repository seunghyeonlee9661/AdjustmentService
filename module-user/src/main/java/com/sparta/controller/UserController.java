package com.sparta.controller;

import com.sparta.dto.UserCreateRequestDTO;
import com.sparta.dto.UserResponseDTO;
import com.sparta.security.UserDetailsImpl;
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
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @GetMapping("/test")
    public String hello() {
        return "Hello, this is User Controller";
    }

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
}