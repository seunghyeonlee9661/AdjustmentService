package com.sparta.video.controller;
import com.sparta.common.dto.VideoDetailResponseDTO;
import com.sparta.common.dto.VideoListResponseDTO;
import com.sparta.user.security.UserDetailsImpl;
import com.sparta.video.service.VideoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "User API")
@RequestMapping("/api")
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/test/videos")
    public String hello() {
        return "Hello, this is Videos Controller";
    }

    /* 비디오 목록 페이지 불러오기 */
    @GetMapping("/videos")
    public ResponseEntity<Page<VideoListResponseDTO>> findVideoList(@RequestParam(value = "page", defaultValue = "0") int page){
        return videoService.getVideoList(page);
    }

    /* 비디오 재생 */
    @GetMapping("/videos/{id}")
    public ResponseEntity<VideoDetailResponseDTO> playVideo(@PathVariable("id") long id, @AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletRequest request){
        return videoService.playVideo(id,userDetails,request);
    }
}