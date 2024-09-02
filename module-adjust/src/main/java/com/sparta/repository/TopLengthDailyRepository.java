package com.sparta.repository;
import com.sparta.entity.TopLengthDaily;
import com.sparta.entity.TopViewDaily;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

public interface TopLengthDailyRepository extends JpaRepository<TopLengthDaily, Long> {
    void deleteByDate(Timestamp date);
}