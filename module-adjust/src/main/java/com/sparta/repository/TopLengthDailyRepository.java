package com.sparta.repository;
import com.sparta.entity.TopLengthDaily;
import com.sparta.entity.TopViewDaily;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopLengthDailyRepository extends JpaRepository<TopLengthDaily, Long> {
}