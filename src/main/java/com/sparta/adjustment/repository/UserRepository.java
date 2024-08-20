//package com.sparta.adjustment.repository;
//import com.sparta.adjustment.entity.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByUsername(String email);
//    Optional<User> findByKakaoId(Long kakaoId);
//    Boolean existsByNickname(String nickname);
//    Boolean existsByEmail(String email);
//}
