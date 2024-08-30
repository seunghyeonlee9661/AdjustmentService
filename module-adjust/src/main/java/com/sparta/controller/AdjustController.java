package com.sparta.controller;

import com.sparta.dto.VideoCreateRequestDTO;
import com.sparta.service.AdjustService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
class AdjustController {
    private final AdjustService adjustService;


    @GetMapping("/test")
    public String hello() {
        return "Hello, this is Adjustment Controller";
    }

    /* 해당 시간 기준 모든 영상의 조회수와 재생 시간을 기록해 일간 조회수를 기록함 */
    @PostMapping("/record")
    public ResponseEntity<String> setDailyViews(){
        return adjustService.setDailyRecord();
    }

    /* 해당 시간 기준 모든 영상의 조회수와 재생 시간을 기록해 일간 조회수를 기록함 */
    @PostMapping("/top/daily")
    public ResponseEntity<String> setDailyTop(){
        return adjustService.setDailyTop();
    }

    /* 해당 시간 기준 모든 영상의 조회수와 재생 시간을 기록해 일간 조회수를 기록함 */
    @PostMapping("/top/weekly")
    public ResponseEntity<String> setWeeklyTop(){
        return adjustService.setWeeklyTop();
    }

    /* 해당 시간 기준 모든 영상의 조회수와 재생 시간을 기록해 일간 조회수를 기록함 */
    @PostMapping("/top/monthly")
    public ResponseEntity<String> setMonthlyTop(){
        return adjustService.setMonthlyTop();
    }
}