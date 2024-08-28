package com.sparta.service;
import com.sparta.dto.VideoCreateRequestDTO;
import com.sparta.dto.VideoCreateResponseDTO;
import com.sparta.dto.VideoDetailResponseDTO;
import com.sparta.dto.VideoListResponseDTO;
import com.sparta.entity.User;
import com.sparta.entity.Video;
import com.sparta.repository.VideoRepository;
import com.sparta.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.jcodec.api.JCodecException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;


@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final RedisService redisService;
    private final FileService fileService;
    private final JCodecService jCodecService;


    @Transactional
    public ResponseEntity<Page<VideoListResponseDTO>> getVideoList(int page){
        Pageable pageable = PageRequest.of(page, 8);
        Page<Video> videoPage = videoRepository.findAll(pageable);
        return ResponseEntity.ok(videoPage.map(VideoListResponseDTO::new));
    }

    @Transactional
    public ResponseEntity<VideoDetailResponseDTO> playVideo(long id, UserDetailsImpl userDetails, HttpServletRequest request){
//        User user = userDetails != null ? userDetails.getUser() : null;
        Video video = videoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No Video Found"));

        // Redis 기반, 사용자 IP로 게시물의 조회수를 1일 1회만 증가시킬 수 있도록 지정
        if (redisService.incrementViewCount(getClientIp(request),Long.toString(id))) { // 사용자 IP를 Redis에서 검색
            video.updateViews(); // Board 엔티티의 조회수 증가 메서드 호출
        }
        return ResponseEntity.ok(new VideoDetailResponseDTO(video));
    }

    @Transactional
    public ResponseEntity<VideoCreateResponseDTO> uploadVideoFile(MultipartFile file) throws IOException, JCodecException {
        File tempFile = File.createTempFile("temp", ".tmp");
        file.transferTo(tempFile);

        // 파일 포맷 확인
        if (!isVideoFile(tempFile)) {
            tempFile.delete();
            throw new IllegalArgumentException("Invalid video file format.");
        }

        String fileUrl = fileService.uploadFile(FileService.VIDEO_UPLOAD_DIR,FileService.VIDEO_URL_DIR,tempFile); // FileService를 통해 파일을 업로드하고 URL을 받음

        String thumbnailUrl = jCodecService.getThumbnail(tempFile); //썸네일 추출

        long duration = JCodecService.getDuration(tempFile); //영상길이 추출

        tempFile.delete(); // 변환된 임시 파일 삭제
        return ResponseEntity.ok(new VideoCreateResponseDTO(fileUrl,thumbnailUrl,duration));
    }

    @Transactional
    public ResponseEntity<String> uploadVideoInfo(VideoCreateRequestDTO requestDTO, UserDetailsImpl userDetails) {

        User user = userDetails.getUser();
        videoRepository.save(new Video(requestDTO,user));
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    // 사용자 IP 확인하는 기능
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    // 파일이 영상 파일인지 확인하는 메서드
    private boolean isVideoFile(File file) {
        // MIME 타입 확인
        try (InputStream is = Files.newInputStream(file.toPath())) {
            String mimeType = Files.probeContentType(file.toPath());
            return mimeType != null && mimeType.startsWith("video");
        } catch (IOException e) {
            return false;
        }
    }
}