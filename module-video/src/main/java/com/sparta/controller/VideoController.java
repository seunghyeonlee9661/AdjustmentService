package com.sparta.controller;
import com.sparta.dto.*;
import com.sparta.security.UserDetailsImpl;
import com.sparta.service.VideoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jcodec.api.JCodecException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    /* 비디오 파일 업로드 */
    @PostMapping("/upload")
    public ResponseEntity<VideoCreateResponseDTO> uploadVideoFile(@RequestPart(value = "file") MultipartFile file) throws IOException {
        return videoService.uploadVideoFile(file);
    }

    /* 비디오 업로드 취소 */
    @DeleteMapping("/upload")
    public ResponseEntity<String> deleteVideoFile(@Valid @RequestBody VideoCancelCreateRequestDTO requestDTO) throws JCodecException, IOException {
        return videoService.cancelVideoFile(requestDTO);
    }

    /* 비디오 정보 업로드 */
    @PostMapping("")
    public ResponseEntity<String> uploadVideoInfo(@Valid @RequestBody VideoCreateRequestDTO requestDTO,@AuthenticationPrincipal UserDetailsImpl userDetails){
        return videoService.uploadVideoInfo(requestDTO,userDetails);
    }

    /* 비디오 재생 */
    @GetMapping("/{id}/play")
    public ResponseEntity<VideoDetailResponseDTO> playVideo(@PathVariable("id") long id,@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletRequest request){
        return videoService.playVideo(id,userDetails,request);
    }

    /* 비디오 중지 */
    @PostMapping("/{id}/pause")
    public ResponseEntity<String> pauseVideo(@PathVariable("id") long id,@AuthenticationPrincipal UserDetailsImpl userDetails,@Valid @RequestBody VideoPauseRequestDTO requestDTO){
        return videoService.pauseVideo(id,userDetails,requestDTO);
    }

    /* 비디오 삭제 */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVideo(@PathVariable("id") long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return videoService.deleteVideo(id,userDetails);
    }
}