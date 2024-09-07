package com.sparta.repository;
import com.sparta.entity.AdList;
import com.sparta.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdListRepository extends JpaRepository<AdList, Long> {
    Optional<AdList> findByAdIdAndVideoId(Long adId,Long videoId);
    List<AdList> findByVideo(Video video);
}