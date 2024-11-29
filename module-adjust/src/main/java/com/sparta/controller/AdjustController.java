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
@RequestMapping("")
class AdjustController {
    private final AdjustService adjustService;

    /* 일간 영상별 영상과 광고 시청수 호출 */
    @GetMapping("/record/all")
    public ResponseEntity<List<DailyRecordResponseDTO>> getDailyRecord(@RequestParam(required = false) String date,@RequestParam(required = false) Long id){
        return adjustService.getDailyRecord(date,id);
    }

    /* 일간 영상별 영상과 광고 시청수 저장 */
    @PostMapping("/record")
    public ResponseEntity<String> setDailyRecord(){
        adjustService.setDailyRecord();
        return ResponseEntity.ok("저장 완료");
    }

    //____batch 성능 비교를 위한 수동 검색 기능_______________

    /* 해당 시간 기준 모든 영상의 조회수와 재생 시간을 기록해 일간 조회수를 호출 */
    @GetMapping("/record")
    public ResponseEntity<List<DailySummaryResponseDTO>> getDailyUserSummary(
            @RequestParam(value = "date", required = false) String date,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 서비스에서 날짜 변환 및 기본값 처리
        return adjustService.getDailySummaryByDate(date, userDetails);
    }


    //____batch 대상 기능을 수동으로 실행_______________

    @PostMapping("/summary")
    public ResponseEntity<String> setDailySummary() {
        adjustService.setDailySummaryWithThreads();
        return ResponseEntity.ok("저장 완료");
    }

    // 만들어진 정산 결과 습득
    @GetMapping("/summary")
    public ResponseEntity<List<DailySummaryResponseDTO>> getDailySummary(
            @RequestParam(value = "date", required = false) String date,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 서비스에서 날짜 변환 및 기본값 처리
        return adjustService.getDailySummaryByDateFromTable(date, userDetails);
    }
}