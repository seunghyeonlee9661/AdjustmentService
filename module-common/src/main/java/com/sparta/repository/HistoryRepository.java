package com.sparta.repository;
import com.sparta.entity.History;
import com.sparta.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, Long> {
    Page<History> findByUserId(Long userId,Pageable pageable);
    Optional <History> findByUserIdAndVideoId(Long Id, Long userId);

    @Query("SELECT COALESCE(SUM(h.watchedLength), 0) FROM History h WHERE h.video = :video")
    Long sumWatchedLengthByVideo(@Param("video") Video video);
}