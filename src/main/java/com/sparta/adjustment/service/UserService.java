//package com.sparta.adjustment.service;
//import com.sparta.adjustment.DTO.UserCreateRequestDTO;
//import com.sparta.adjustment.entity.User;
//import com.sparta.adjustment.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///*
//작성자 : 이승현
//사용자 관련 서비스 처리
//*/
//@Service
//@RequiredArgsConstructor
//public class UserService {
//
//    private static final Log log = LogFactory.getLog(UserService.class);
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    /* 회원가입 */
//    @Transactional
//    public ResponseEntity<String> createUser(UserCreateRequestDTO userCreateRequestDTO){
//        if (userRepository.findByUsername(userCreateRequestDTO.getEmail()).isPresent()) throw new IllegalArgumentException("중복된 Email 입니다.");
//        if (!userCreateRequestDTO.getPassword().equals(userCreateRequestDTO.getPasswordCheck())) throw new IllegalArgumentException("비밀번호 확인이 일치하지 않습니다.");
//        User user = new User(userCreateRequestDTO,passwordEncoder.encode(userCreateRequestDTO.getPassword()));
//        userRepository.save(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
//    }
//
////    /* 회원 탈퇴*/
////    @Transactional
////    public ResponseEntity<String> removeUser(UserDetailsImpl userDetails, HttpServletResponse res) throws IOException {
////        User user = userDetails.getUser();
////        // 카카오 계정이 아니고, 이미지가 있는 경우에만 이미지 삭제
////        if (user.getKakaoId() == null && user.getImage() != null) {
////            s3Service.deleteFileByUrl(user.getImage());
////        }
////        userRepository.delete(user);
////        jwtUtil.removeJwtCookie(res);
////        return ResponseEntity.ok().body("User deleted successfully");
////    }
//
////    /* 회원 수정 */
////    @Transactional
////    public ResponseEntity<String> updateUserInfo(UserDetailsImpl userDetails, String nickname,boolean deleteImage, MultipartFile file) throws IOException {
////        User user = userDetails.getUser();
////        // 사용자의 기존 nickname과 다르면서 현재 다른 사람이 nickname을 쓰고 있는 경우 : nickname 중복
////        if(!user.getNickname().equals(nickname) && userRepository.existsByNickname(nickname)) return ResponseEntity.status(HttpStatus.CONFLICT).body("Nickname is already taken.");
////
////        String url = user.getImage();
////        log.info("사용자 이미지 확인");
////        if(file == null || file.isEmpty()){
////            if(deleteImage) {
////                url = null;
////                log.info("이미지 삭제");
////                s3Service.deleteFileByUrl(user.getImage());
////            }
////        }else{
////            log.info("이미지 수정");
////            if(user.getImage() != null) s3Service.deleteFileByUrl(user.getImage());
////            File webPFile = imageTransformService.convertToWebP(file);
////            log.info("이미지 업로드");
////            url = s3Service.uploadFile(webPFile);
////        }
////        log.info("이미지 url : " + url);
////        user.updateInfo(nickname,url);
////        userRepository.save(user);
////        return ResponseEntity.ok().body("User updated successfully");
////    }
////
////    /* 회원 비밀번호 수정 */
////    @Transactional
////    public ResponseEntity<String> updateUserPassword(UserDetailsImpl userDetails, UserPasswordUpdateRequestDTO userPasswordUpdateRequestDTO) {
////        User user = userDetails.getUser();
////        if(!userPasswordUpdateRequestDTO.getNewPassword().equals(userPasswordUpdateRequestDTO.getNewPasswordCheck()))
////            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("새 비밀번호 확인이 일치하지 않습니다.");
////        if(!passwordEncoder.matches(userPasswordUpdateRequestDTO.getCurrentPassword(),user.getPassword()))
////            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("현재 비밀번호가 올바르지 않습니다.");
////        user.updatePassword(passwordEncoder.encode(userPasswordUpdateRequestDTO.getNewPassword()));
////        userRepository.save(user);
////        return ResponseEntity.ok().body("User updated successfully");
////    }
////
////    /* 사용자의 게시물 검색 기능 */
////    public ResponseEntity<Page<SimpleBoardResponseDTO>> findUserBoard(UserDetailsImpl userDetails, int page, Integer pty, Integer sky, String keyword){
////        Pageable pageable = PageRequest.of(page, 8, Sort.by(Sort.Order.desc("id")));
////        Page<Board> boardPage  = boardRepository.findByUserId(userDetails.getUser().getId(),pty,sky,keyword,pageable);
////        return ResponseEntity.ok(boardPage.map(SimpleBoardResponseDTO::new));
////    }
//}
