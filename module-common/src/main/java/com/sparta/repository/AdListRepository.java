package com.sparta.repository;
import com.sparta.entity.AdList;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdListRepository extends JpaRepository<AdList, Long> {
    Optional<AdList> findByAdIdAndVideoId(Long adId,Long videoId);
}