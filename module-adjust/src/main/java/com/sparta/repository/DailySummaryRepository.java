package com.sparta.repository;
import com.sparta.entity.DailySummary;
import com.sparta.entity.User;
import com.sparta.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface DailySummaryRepository extends JpaRepository<DailySummary, Long> {
    Optional<DailySummary> findByDateAndVideo(Timestamp date, Video video);
    List<DailySummary> findByDate(Timestamp date);
    List<DailySummary> findAllByUserAndDate(User user, Timestamp date);


}