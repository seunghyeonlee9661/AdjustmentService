package com.sparta.adjustment.controller;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "User API")
@RequestMapping("/api")
public class HelloController {
    /* 사용자 정보 반환 */
    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello World!!");
    }
}
