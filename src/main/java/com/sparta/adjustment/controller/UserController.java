//package com.sparta.adjustment.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.sparta.adjustment.dto.UserCreateRequestDTO;
//import com.sparta.adjustment.dto.UserResponseDTO;
//import com.sparta.adjustment.security.UserDetailsImpl;
//import com.sparta.adjustment.service.KakaoLoginService;
//import com.sparta.adjustment.service.UserService;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//
//@RequiredArgsConstructor
//@RestController
//@Tag(name = "User API")
//@RequestMapping("/api")
//public class UserController {
//    private final UserService userService;
//    private final KakaoLoginService kakaoLoginService;
//
//    /* 사용자 정보 반환 */
//    @GetMapping("/users")
//    public ResponseEntity<UserResponseDTO> findUser(@AuthenticationPrincipal UserDetailsImpl userDetails){
//        return ResponseEntity.ok(new UserResponseDTO(userDetails.getUser()));
//    }
//
//    /* 사용자 회원가입 */
//    @PostMapping("/users")
//    public ResponseEntity<String> createUser(@Valid @RequestBody UserCreateRequestDTO requestDTO) {
//        return userService.createUser(requestDTO);
//    }
//
//    /* 사용자 회원탈퇴 */
//    @DeleteMapping("/users")
//    public ResponseEntity<String> removeUser(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse res) throws IOException {
//        return userService.removeUser(userDetails,res);
//    }
//
//    /* 업로더 전환 요청 */
//    @PostMapping("/uploader")
//    public ResponseEntity<String> requestUploader(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return userService.requestUploader(userDetails);
//    }
//
//    /* 업로더 전환 */
//    @GetMapping("/uploader")
//    public ResponseEntity<String> updateUploader(@AuthenticationPrincipal UserDetailsImpl userDetails,@RequestParam(value="code") String code) {
//        return userService.updateUploader(userDetails,code);
//    }
//
//    /* 사용자 정보 추가 */
//    @PostMapping("/kakao/callback")
//    public ResponseEntity<String> KakaoLogin(@RequestParam String code, HttpServletResponse response)  throws JsonProcessingException {
//        return kakaoLoginService.kakaoLogin(code,response);
//    }
//}
