package com.sparta.repository;
import com.sparta.entity.TopLengthDaily;
import com.sparta.entity.TopLengthWeekly;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

public interface TopLengthWeeklyRepository extends JpaRepository<TopLengthWeekly, Long> {
    void deleteByDate(Timestamp date);
}