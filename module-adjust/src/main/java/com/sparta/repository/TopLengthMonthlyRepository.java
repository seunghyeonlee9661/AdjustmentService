package com.sparta.repository;
import com.sparta.entity.TopLengthMonthly;
import com.sparta.entity.TopLengthWeekly;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

public interface TopLengthMonthlyRepository extends JpaRepository<TopLengthMonthly, Long> {
    void deleteByDate(Timestamp date);
}