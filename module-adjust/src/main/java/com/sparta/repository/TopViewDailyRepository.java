package com.sparta.repository;
import com.sparta.entity.TopViewDaily;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

public interface TopViewDailyRepository extends JpaRepository<TopViewDaily, Long> {
    void deleteByDate(Timestamp date);
}