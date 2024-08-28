package com.sparta.service;
import com.sparta.dto.VideoCreateRequestDTO;
import com.sparta.dto.VideoCreateResponseDTO;
import com.sparta.dto.VideoDetailResponseDTO;
import com.sparta.dto.VideoListResponseDTO;
import com.sparta.entity.History;
import com.sparta.entity.User;
import com.sparta.entity.Video;
import com.sparta.repository.HistoryRepository;
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
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final HistoryRepository historyRepository;
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
        Video video = videoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No Video Found"));
        User user = userDetails != null ? userDetails.getUser() : null;

        Long watchedDuration = 0L;
        // 사용자가 있는 경우 History를 조회
        if (userDetails != null) {
            // History를 Optional로 조회하고 watchedDuration을 설정
            watchedDuration = historyRepository.findByUserIdAndVideoId(userDetails.getUser().getId(), video.getId()).map(History::getWatchedDuration).orElse(0L);
        }
        // Redis 기반, 사용자 IP로 게시물의 조회수를 1일 1회만 증가시킬 수 있도록 지정
        if (redisService.incrementViewCount(getClientIp(request),Long.toString(id))) {
            video.updateViews();
        }
        return ResponseEntity.ok(new VideoDetailResponseDTO(video,watchedDuration));
    }

    public ResponseEntity<VideoCreateResponseDTO> uploadVideoFile(MultipartFile file) throws IOException, JCodecException {
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
        //썸네일 추출
        String thumbnailUrl = jCodecService.getThumbnail(tempFile);
        //영상길이 추출
        long duration = JCodecService.getDuration(tempFile);
        // 변환된 임시 파일 삭제
        tempFile.delete();
        return ResponseEntity.ok(new VideoCreateResponseDTO(fileUrl,thumbnailUrl,duration));
    }

    @Transactional
    public ResponseEntity<String> uploadVideoInfo(VideoCreateRequestDTO requestDTO, UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        videoRepository.save(new Video(requestDTO,user));
        return ResponseEntity.status(HttpStatus.CREATED).body("video created successfully");
    }

    @Transactional
    public ResponseEntity<String> deleteVideo(Long id, UserDetailsImpl userDetails) {
        Video video = videoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No Video Found"));
        if(!userDetails.getUser().equals(video.getUser())) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not owner of this video");
        fileService.deleteFileByUrl(FileService.VIDEO_UPLOAD_DIR,FileService.VIDEO_URL_DIR,video.getUrl());
        fileService.deleteFileByUrl(FileService.THUMBNAIL_UPLOAD_DIR,FileService.THUMBNAIL_URL_DIR,video.getThumbnail());
        videoRepository.delete(video);
        return ResponseEntity.status(HttpStatus.CREATED).body("video created successfully");
    }


    // 사용자 IP 확인하는 기능
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}