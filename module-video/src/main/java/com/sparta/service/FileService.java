package com.sparta.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class FileService {
    public static final String VIDEO_UPLOAD_DIR = "/var/www/uploads/adjustment/video/";
    public static final String VIDEO_URL_DIR = "http://dltmdgus9661.iptime.org/uploads/adjustment/video/";
    public static final String THUMBNAIL_UPLOAD_DIR = "/var/www/uploads/adjustment/thumbnail/";
    public static final String THUMBNAIL_URL_DIR = "http://dltmdgus9661.iptime.org/uploads/adjustment/thumbnail/";

    /* 파일 업로드 */
    public String uploadFile(String upload_dr,String url_dir, File file) throws IOException {
        String key = generateUniqueFileName(upload_dr,file.getName());
        File destinationFile = new File(upload_dr + key);

        // 파일 경로 확인
        System.out.println("Source file path: " + file.getAbsolutePath());
        System.out.println("Destination file path: " + destinationFile.getAbsolutePath());

        // 목적지 디렉토리 존재 여부 확인 및 생성
        File destinationDir = destinationFile.getParentFile();
        if (!destinationDir.exists()) {
            if (!destinationDir.mkdirs()) {
                throw new IOException("Failed to create directory: " + destinationDir.getAbsolutePath());
            }
        }

        if (!file.exists()) {
            throw new FileNotFoundException("Source file does not exist: " + file.getAbsolutePath());
        }

        try {
            System.out.println(" 파일 복사 시도 " + destinationFile.getAbsolutePath());
            Files.copy(file.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println(" 파일 복사 성공 " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace(); // 또는 로깅 프레임워크를 사용하여 로그를 기록
            throw e; // 예외를 다시 던지거나 적절히 처리
        }
        return String.format(url_dir + "%s", key);
    }

    /* 서버에 없는 유니크 이름을 생성하는 기능 */
    private String generateUniqueFileName(String upload_dr,String originalFileName) {
        String extension = getFileExtension(originalFileName);
        String key;
        File file;
        do {
            key = UUID.randomUUID().toString() + "." + extension;
            file = new File(upload_dr + key);
        } while (file.exists());
        return key;
    }

    /* 파일 확장자 추출 */
    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        return (lastDotIndex == -1) ? "" : fileName.substring(lastDotIndex + 1);
    }

    /* 파일 삭제 기능 */
    public void deleteFileByUrl(String upload_dr,String url_dir,String fileUrl) {
        String key = extractKeyFromUrl(url_dir,fileUrl);
        File file = new File(upload_dr + key);
        if (file.exists()) {
            file.delete();
        } else {
            throw new IllegalArgumentException("File not found: " + fileUrl);
        }
    }

    // 파일이 영상 파일인지 확인하는 메서드
    public boolean isVideoFile(File file) {
        // MIME 타입 확인
        try (InputStream is = Files.newInputStream(file.toPath())) {
            String mimeType = Files.probeContentType(file.toPath());
            return mimeType != null && mimeType.startsWith("video");
        } catch (IOException e) {
            return false;
        }
    }

    /* URL로부터 파일 이름을 추출하는 기능 */
    private String extractKeyFromUrl(String url_dir,String fileUrl) {
        String pattern = url_dir + "(.*)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(fileUrl);
        if (m.find()) {
            return m.group(1);
        } else {
            throw new IllegalArgumentException("Invalid URL: " + fileUrl);
        }
    }
}
