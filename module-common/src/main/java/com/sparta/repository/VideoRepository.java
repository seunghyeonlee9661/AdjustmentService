package com.sparta.repository;
import com.sparta.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VideoRepository  extends JpaRepository<Video, Long> {
    Page<Video> findAll(Pageable pageable);
    Page<Video> findByUserId(Long userId,Pageable pageable);
}