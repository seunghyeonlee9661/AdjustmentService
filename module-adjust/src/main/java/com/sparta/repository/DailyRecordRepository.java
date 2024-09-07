package com.sparta.repository;
import com.sparta.entity.DailyRecord;
import com.sparta.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface DailyRecordRepository extends JpaRepository<DailyRecord, Long>  {
    Optional<DailyRecord> findByDateAndVideo(Timestamp date, Video video);
    List<DailyRecord> findByDate(Timestamp date);
}