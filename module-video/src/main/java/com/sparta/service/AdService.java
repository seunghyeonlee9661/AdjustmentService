package com.sparta.service;

import com.sparta.dto.*;
import com.sparta.entity.Ad;
import com.sparta.entity.AdList;
import com.sparta.entity.User;
import com.sparta.entity.Video;
import com.sparta.repository.AdListRepository;
import com.sparta.repository.AdRepository;
import com.sparta.repository.VideoRepository;
import com.sparta.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AdService {

    private final AdRepository adRepository;
    private final AdListRepository adListRepository;
    private final VideoRepository videoRepository;
    private final FileService fileService;
    private final RedisService redisService;

    public ResponseEntity<AdResponseDTO> findAd (long id, UserDetailsImpl userDetails) throws IllegalAccessException {
        Ad ad = adRepository.findById(id).orElseThrow(()-> new IllegalAccessException("No Video Found"));
        if(!ad.getUser().equals(userDetails.getUser())) throw new IllegalAccessException("Failed to save the file: ");
        return ResponseEntity.ok(new AdResponseDTO(ad));
    }

    public ResponseEntity<String> uploadAdFile(MultipartFile file) throws IOException {
        String originalFileName = file.getOriginalFilename();

        // /tmp 디렉토리에 파일 생성
        File tempFile = new File("/tmp/" + originalFileName);
        file.transferTo(tempFile);
        System.out.println("Temporary file path: " + tempFile.getAbsolutePath());

        // 파일이 성공적으로 저장되었는지 확인
        if (!tempFile.exists()) throw new IOException("Failed to save the file: " + tempFile.getAbsolutePath());

        // 파일 포맷 확인
        if (!fileService.isVideoFile(tempFile)) {
            tempFile.delete();
            throw new IllegalArgumentException("Invalid video file format.");
        }
        // FileService를 통해 파일을 업로드하고 URL을 받음
        String fileUrl = fileService.uploadFile(FileService.VIDEO_UPLOAD_DIR,FileService.VIDEO_URL_DIR,tempFile);

        //TODO: 영상 길이 제한을 위한 기능 추가 필요
        
        // 변환된 임시 파일 삭제
        tempFile.delete();
        return ResponseEntity.ok(fileUrl);
    }

    @Transactional
    public ResponseEntity<String> uploadAdInfo(AdCreateRequestDTO requestDTO, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        adRepository.save(new Ad(requestDTO,user));
        return ResponseEntity.status(HttpStatus.CREATED).body("Ad created successfully");
    }

    @Transactional
    public ResponseEntity<String> cancelAdFile(String url) {
        fileService.deleteFileByUrl(FileService.VIDEO_UPLOAD_DIR,FileService.VIDEO_URL_DIR,url);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ad create canceled successfully");
    }



    @Transactional
    public ResponseEntity<String> deleteAd(Long id, UserDetailsImpl userDetails) {
        Ad ad = adRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No Ad Found"));
        if(!userDetails.getUser().getId().equals(ad.getUser().getId())) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not owner of this video");
        fileService.deleteFileByUrl(FileService.VIDEO_UPLOAD_DIR,FileService.VIDEO_URL_DIR,ad.getUrl());
        adRepository.delete(ad);
        return ResponseEntity.status(HttpStatus.CREATED).body("Ad delete successfully");
    }

    @Transactional
    public ResponseEntity<SimpleAdResponseDTO> playAd (long video_id,long ad_id, HttpServletRequest request) throws IllegalAccessException {
        Video video = videoRepository.findById(video_id).orElseThrow(()-> new IllegalAccessException("No Video Found"));
        Ad ad = adRepository.findById(ad_id).orElseThrow(()-> new IllegalAccessException("No Video Found"));
        AdList adList = adListRepository.findByAdIdAndVideoId(ad.getId(),video.getId()).orElseThrow(()-> new IllegalAccessException("Ad Connection Not Found"));

        // Redis 기반, 사용자 IP로 게시물의 조회수를 1일 1회만 증가시킬 수 있도록 지정
        if (redisService.incrementViewCount(redisService.getClientIp(request),RedisService.AD_VIEW_LIMIT_PREFIX,RedisService.AD_VIEW_LIMIT_DURATION, String.valueOf(adList.getId()))) {
            video.updateViews();
        }
        adList.update();
        return ResponseEntity.ok(new SimpleAdResponseDTO(ad));
    }
}
