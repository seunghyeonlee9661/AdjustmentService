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

    /* 광고 파일 업로드 */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadAdFile(@RequestPart(value = "file") MultipartFile file) throws JCodecException, IOException {
        return adService.uploadAdFile(file);
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
}