package com.sparta.service;

import com.sparta.dto.AdCreateRequestDTO;
import com.sparta.dto.VideoCreateRequestDTO;
import com.sparta.dto.VideoCreateResponseDTO;
import com.sparta.entity.Ad;
import com.sparta.entity.User;
import com.sparta.entity.Video;
import com.sparta.repository.AdRepository;
import com.sparta.repository.VideoRepository;
import com.sparta.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.jcodec.api.JCodecException;
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
public class AdService {

    private final AdRepository adRepository;
    private final FileService fileService;


    public ResponseEntity<String> uploadAdFile(MultipartFile file) throws IOException, JCodecException {
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
        return ResponseEntity.status(HttpStatus.CREATED).body("video created successfully");
    }

    @Transactional
    public ResponseEntity<String> deleteAd(Long id, UserDetailsImpl userDetails) {
        Ad ad = adRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("No Ad Found"));
        if(!userDetails.getUser().equals(ad.getUser())) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not owner of this video");
        fileService.deleteFileByUrl(FileService.VIDEO_UPLOAD_DIR,FileService.VIDEO_URL_DIR,ad.getUrl());
        adRepository.delete(ad);
        return ResponseEntity.status(HttpStatus.CREATED).body("video created successfully");
    }

}
