package com.sparta.controller;
import com.sparta.dto.VideoDetailResponseDTO;
import com.sparta.dto.VideoListResponseDTO;
import com.sparta.security.UserDetailsImpl;
import com.sparta.service.VideoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class VideoController {
    private final VideoService videoService;

    @GetMapping("/test")
    public String hello() {
        return "Hello, this is Video Controller";
    }

    /* 비디오 목록 페이지 불러오기 */
    @GetMapping("")
    public ResponseEntity<Page<VideoListResponseDTO>> findVideoList(@RequestParam(value = "page", defaultValue = "0") int page){
        return videoService.getVideoList(page);
    }

    /* 비디오 재생 */
    @GetMapping("/{id}")
    public ResponseEntity<VideoDetailResponseDTO> playVideo(@PathVariable("id") long id, @AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletRequest request){
        return videoService.playVideo(id,userDetails,request);
    }
}