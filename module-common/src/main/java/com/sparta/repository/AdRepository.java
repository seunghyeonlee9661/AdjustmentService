package com.sparta.repository;
import com.sparta.entity.Ad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdRepository extends JpaRepository<Ad, Long> {
    Page<Ad> findAll(Pageable pageable);
    Page<Ad> findByUserId(Long userId,Pageable pageable);
}