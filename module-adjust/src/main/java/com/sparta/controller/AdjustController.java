package com.sparta.controller;

import com.sparta.dto.DailyRecordResponseDTO;
import com.sparta.dto.TopLengthResponseDTO;
import com.sparta.dto.TopViewResponseDTO;
import com.sparta.service.AdjustService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
class AdjustController {
    private final AdjustService adjustService;

    /* 해당 시간 기준 모든 영상의 조회수와 재생 시간을 기록해 일간 조회수를 기록함 */
    @GetMapping("/record")
    public ResponseEntity<List<DailyRecordResponseDTO>> getDailyRecord(@RequestParam(required = false) String date){
        return adjustService.getDailyRecord(date);
    }

    /* 해당 시간 기준 모든 영상의 조회수와 재생 시간을 기록해 일간 조회수를 기록함 */
    @GetMapping("/top/view")
    public ResponseEntity<List<TopViewResponseDTO>> getTopView(@RequestParam(value = "option", defaultValue = "") String option){
        return adjustService.getTopView(option);
    }

    /* 해당 시간 기준 모든 영상의 조회수와 재생 시간을 기록해 일간 조회수를 기록함 */
    @GetMapping("/top/length")
    public ResponseEntity<List<TopLengthResponseDTO>> getTopLength(@RequestParam(value = "option", defaultValue = "") String option){
        return adjustService.getTopLength(option);
    }


    /* 해당 시간 기준 모든 영상의 조회수와 재생 시간을 기록해 일간 조회수를 기록함 */
    @PostMapping("/record")
    public ResponseEntity<String> setDailyRecord(){
         adjustService.setDailyRecord();
         return ResponseEntity.ok("저장 완료");
    }

    /* 해당 시간 기준 모든 영상의 조회수와 재생 시간을 기록해 일간 조회수를 기록함 */
    @PostMapping("/top/daily")
    public ResponseEntity<String> setDailyTop(){
        adjustService.setDailyTop();
        return ResponseEntity.ok("저장 완료");
    }

    /* 해당 시간 기준 모든 영상의 조회수와 재생 시간을 기록해 주간 조회수를 기록함 */
    @PostMapping("/top/weekly")
    public ResponseEntity<String> setWeeklyTop(){
        adjustService.setWeeklyTop();
        return ResponseEntity.ok("저장 완료");
    }

    /* 해당 시간 기준 모든 영상의 조회수와 재생 시간을 기록해 월간 조회수를 기록함 */
    @PostMapping("/top/monthly")
    public ResponseEntity<String> setMonthlyTop(){
        adjustService.setMonthlyTop();
        return ResponseEntity.ok("저장 완료");
    }
}