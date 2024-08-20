//package com.sparta.adjustment.service;
//
//import com.sparta.adjustment.dto.UserCreateRequestDTO;
//import com.sparta.adjustment.entity.User;
//import com.sparta.adjustment.repository.UserRepository;
//import com.sparta.adjustment.security.JwtUtil;
//import com.sparta.adjustment.security.UserDetailsImpl;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.IOException;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class UserService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final EmailService emailService;
//    private final RedisService redisService;
//    private final JwtUtil jwtUtil;
//
//    /* 회원가입 */
//    @Transactional
//    public ResponseEntity<String> createUser(UserCreateRequestDTO userCreateRequestDTO){
//        if (userRepository.findByUsername(userCreateRequestDTO.getUsername()).isPresent()) throw new IllegalArgumentException("중복된 Email 입니다.");
//        if (!userCreateRequestDTO.getPassword().equals(userCreateRequestDTO.getPasswordCheck())) throw new IllegalArgumentException("비밀번호 확인이 일치하지 않습니다.");
//        User user = new User(userCreateRequestDTO,passwordEncoder.encode(userCreateRequestDTO.getPassword()));
//        userRepository.save(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
//    }
//
//    /* 회원 탈퇴*/
//    @Transactional
//    public ResponseEntity<String> removeUser(UserDetailsImpl userDetails, HttpServletResponse res) throws IOException {
//        User user = userDetails.getUser();
//        userRepository.delete(user);
//        jwtUtil.removeJwtCookie(res);
//        return ResponseEntity.ok().body("User deleted successfully");
//    }
//
//    public ResponseEntity<String> requestUploader(UserDetailsImpl userDetails) {
//        String code = UUID.randomUUID().toString();
//        String username = userDetails.getUsername();
//        if(emailService.sendUploaderRequestEmail(username,code)){ // 메일 발송
//            redisService.save(RedisService.UPLOADER_REQUEST_PREFIX,code,username,RedisService.UPLOADER_REQUEST_DURATION); // Redis에 저장
//            return ResponseEntity.ok("메일 전송이 완료되었습니다.");
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("메일 전송에 실패했습니다.");
//    }
//
//    @Transactional
//    public ResponseEntity<String> updateUploader(UserDetailsImpl userDetails,String code) {
//        String username = redisService.get(RedisService.UPLOADER_REQUEST_PREFIX,code);
//        User user = userDetails.getUser();
//        if(user.getUsername().equals(username)){
//            user.updateRole();
//            return ResponseEntity.ok("사용자 권한이 변경되었습니다.");
//        }else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("코드가 올바르지 않습니다.");
//        }
//    }
//}
