package com.sparta.repository;
import com.sparta.entity.History;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface HistoryRepository extends JpaRepository<History, Long> {
    Page<History> findByUserId(Long userId,Pageable pageable);
    Optional <History> findByUserIdAndVideoId(Long Id, Long userId);
}