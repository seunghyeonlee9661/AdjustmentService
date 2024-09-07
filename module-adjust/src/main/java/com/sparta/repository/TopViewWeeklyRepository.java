package com.sparta.repository;
import com.sparta.entity.TopViewDaily;
import com.sparta.entity.TopViewWeekly;
import com.sparta.entity.base.TopViewBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

public interface TopViewWeeklyRepository extends JpaRepository<TopViewWeekly, Long> {
    void deleteByDate(Timestamp date);
}