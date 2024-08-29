package com.sparta.controller;
import com.sparta.dto.*;
import com.sparta.security.UserDetailsImpl;
import com.sparta.service.AdService;
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
@RequestMapping("/api/ads")
public class AdController {
    private final AdService adService;

    /* 광고 정보 확인 */
    @GetMapping("/{id}")
    public ResponseEntity<AdResponseDTO> uploadAdFile(@PathVariable("id") long id,@AuthenticationPrincipal UserDetailsImpl userDetails) throws IllegalAccessException {
        return adService.findAd(id,userDetails);
    }

    /* 광고 파일 업로드 */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadAdFile(@RequestPart(value = "file") MultipartFile file) throws IOException {
        return adService.uploadAdFile(file);
    }

    /* 비디오 업로드 취소 */
    @DeleteMapping("/upload")
    public ResponseEntity<String> deleteAdFile(@RequestParam String url){
        return adService.cancelAdFile(url);
    }

    /* 광고 정보 업로드 */
    @PostMapping("")
    public ResponseEntity<String> uploadAdInfo(@Valid @RequestBody AdCreateRequestDTO requestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return adService.uploadAdInfo(requestDTO,userDetails);
    }

    /* 광고 삭제 */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAd(@PathVariable("id") long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return adService.deleteAd(id,userDetails);
    }

    /* 비디오 광고 재생 */
    @GetMapping("/{video_id}/{ad_id}")
    public ResponseEntity<String> playAd(@PathVariable("video_id") long video_id,@PathVariable("ad_id") long ad_id) throws IllegalAccessException {
        return adService.playAd(video_id,ad_id);
    }
}