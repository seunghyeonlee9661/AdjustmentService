package com.sparta.repository;
import com.sparta.entity.TopViewDaily;
import com.sparta.entity.TopViewMonthly;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

public interface TopViewMonthlyRepository extends JpaRepository<TopViewMonthly, Long> {
    void deleteByDate(Timestamp date);
}