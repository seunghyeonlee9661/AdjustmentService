package com.sparta.controller;

import com.sparta.dto.DailyRecordResponseDTO;
import com.sparta.dto.DailySummaryResponseDTO;
import com.sparta.entity.DailySummary;
import com.sparta.security.UserDetailsImpl;
import com.sparta.service.AdjustService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
class AdjustController {
    private final AdjustService adjustService;

    /* 일간 영상별 영상과 광고 시청수 호출 */
    @GetMapping("record")
    public ResponseEntity<List<DailyRecordResponseDTO>> getDailyRecord(@RequestParam(required = false) String date){
        return adjustService.getDailyRecord(date);
    }

    /* 일간 영상별 영상과 광고 시청수 저장 */
    @PostMapping("record")
    public ResponseEntity<String> setDailyRecord(){
        adjustService.setDailyRecord();
        return ResponseEntity.ok("저장 완료");
    }

    //____batch 성능 비교를 위한 수동 검색 기능_______________

    /* 해당 시간 기준 모든 영상의 조회수와 재생 시간을 기록해 일간 조회수를 호출 */
    @GetMapping("{date}")
    public ResponseEntity<List<DailySummaryResponseDTO>>  getDailySummary(@PathVariable(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 서비스에서 해당 날짜의 DailySummary 조회
        return adjustService.getDailySummaryByDate(date,userDetails);
    }

    //____batch 대상 기능을 수동으로 실행_______________

    @PostMapping("summary")
    public ResponseEntity<String> setDailySummary(){
        adjustService.setDailySummary();
        return ResponseEntity.ok("저장 완료");
    }

//    /* 해당 시간 기준 모든 영상의 조회수와 재생 시간을 기록해 일간 조회수를 호출 */
//    @GetMapping("top/view")
//    public ResponseEntity<List<TopViewResponseDTO>> getTopView(@RequestParam(value = "option", defaultValue = "") String option){
//        return adjustService.getTopView(option);
//    }
//
//    /* 해당 시간 기준 모든 영상의 조회수와 재생 시간을 기록해 일간 조회수를 기록함 */
//    @PostMapping("top/daily")
//    public ResponseEntity<String> setDailyTop(){
//        adjustService.setDailyTop();
//        return ResponseEntity.ok("저장 완료");
//    }
//
//    /* 해당 시간 기준 모든 영상의 조회수와 재생 시간을 기록해 주간 조회수를 기록함 */
//    @PostMapping("top/weekly")
//    public ResponseEntity<String> setWeeklyTop(){
//        adjustService.setWeeklyTop();
//        return ResponseEntity.ok("저장 완료");
//    }
//
//    /* 해당 시간 기준 모든 영상의 조회수와 재생 시간을 기록해 월간 조회수를 기록함 */
//    @PostMapping("top/monthly")
//    public ResponseEntity<String> setMonthlyTop(){
//        adjustService.setMonthlyTop();
//        return ResponseEntity.ok("저장 완료");
//    }



}