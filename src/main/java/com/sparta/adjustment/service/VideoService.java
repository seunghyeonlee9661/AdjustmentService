//package com.sparta.adjustment.service;
//import com.sparta.adjustment.dto.VideoDetailResponseDTO;
//import com.sparta.adjustment.dto.VideoListResponseDTO;
//import com.sparta.adjustment.entity.User;
//import com.sparta.adjustment.entity.Video;
//import com.sparta.adjustment.repository.VideoRepository;
//import com.sparta.adjustment.security.UserDetailsImpl;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@Service
//@RequiredArgsConstructor
//public class VideoService {
//    private final VideoRepository videoRepository;
//    private RedisService redisService;
//
//
//    @Transactional
//    public ResponseEntity<Page<VideoListResponseDTO>> getVideoList(int page){
//        Pageable pageable = PageRequest.of(page, 8);
//        Page<Video> videoPage = videoRepository.findAll(pageable);
//        return ResponseEntity.ok(videoPage.map(VideoListResponseDTO::new));
//    }
//
//    @Transactional
//    public ResponseEntity<VideoDetailResponseDTO> playVideo(long id, UserDetailsImpl userDetails, HttpServletRequest request){
//        User user = userDetails != null ? userDetails.getUser() : null;
//        Video video = videoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No Video Found"));
//
//        // Redis 기반, 사용자 IP로 게시물의 조회수를 1일 1회만 증가시킬 수 있도록 지정
//        if (redisService.incrementViewCount(getClientIp(request),Long.toString(id))) { // 사용자 IP를 Redis에서 검색
//            video.updateViews(); // Board 엔티티의 조회수 증가 메서드 호출
//        }
//        return ResponseEntity.ok(new VideoDetailResponseDTO(video));
//    }
//
//
//    // 사용자 IP 확인하는 기능
//    private String getClientIp(HttpServletRequest request) {
//        String ip = request.getHeader("X-Forwarded-For");
//        if (ip == null || ip.isEmpty()) {
//            ip = request.getRemoteAddr();
//        }
//        return ip;
//    }
//
//
//
//}
