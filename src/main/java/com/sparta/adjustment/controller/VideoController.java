//package com.sparta.adjustment.controller;
//import com.sparta.adjustment.dto.VideoDetailResponseDTO;
//import com.sparta.adjustment.dto.VideoListResponseDTO;
//import com.sparta.adjustment.security.UserDetailsImpl;
//import com.sparta.adjustment.service.VideoService;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.*;
//
//@RequiredArgsConstructor
//@RestController
//@Tag(name = "User API")
//@RequestMapping("/api/videos")
//public class VideoController {
//
//    private final VideoService videoService;
//
//    /* 비디오 목록 페이지 불러오기 */
//    @GetMapping("")
//    public ResponseEntity<Page<VideoListResponseDTO>> findVideoList(@RequestParam(value = "page", defaultValue = "0") int page){
//        return videoService.getVideoList(page);
//    }
//
//    /* 비디오 재생 */
//    @GetMapping("/{id}")
//    public ResponseEntity<VideoDetailResponseDTO> playVideo(@PathVariable("id") long id, @AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletRequest request){
//        return videoService.playVideo(id,userDetails,request);
//    }
//}
