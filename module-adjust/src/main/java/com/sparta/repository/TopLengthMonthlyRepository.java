package com.sparta.repository;
import com.sparta.entity.TopLengthMonthly;
import com.sparta.entity.TopLengthWeekly;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopLengthMonthlyRepository extends JpaRepository<TopLengthMonthly, Long> {
}