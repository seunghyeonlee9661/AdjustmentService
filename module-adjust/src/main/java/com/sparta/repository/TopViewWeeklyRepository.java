package com.sparta.repository;
import com.sparta.entity.TopViewDaily;
import com.sparta.entity.TopViewWeekly;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopViewWeeklyRepository extends JpaRepository<TopViewWeekly, Long> {
}