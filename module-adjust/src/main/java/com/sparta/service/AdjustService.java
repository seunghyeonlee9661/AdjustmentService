package com.sparta.service;

import com.sparta.dto.DailyRecordResponseDTO;
import com.sparta.dto.DailySummaryResponseDTO;
import com.sparta.entity.*;
import com.sparta.repository.*;
import com.sparta.security.UserDetailsImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class AdjustService {

    private final VideoRepository videoRepository;
    private final DailyRecordRepository dailyRecordRepository;
    private final DailySummaryRepository dailySummaryRepository;

    public ResponseEntity<List<DailyRecordResponseDTO>> getDailyRecord(String date, Long videoId) {
        Timestamp timestamp = null;
        try {
            // date가 주어지면 Timestamp로 변환
            if (date != null) timestamp = Timestamp.valueOf(LocalDate.parse(date).atStartOfDay());
        } catch (DateTimeParseException ignored) {}

        // videoId가 주어졌다면 해당 videoId로 필터링
        List<DailyRecord> records;
        if (timestamp != null && videoId != null) {
            // 날짜와 videoId로 필터링
            records = dailyRecordRepository.findByDateAndVideoIdOrderByDateDesc(timestamp, videoId);
        } else if (timestamp != null) {
            // 날짜로만 필터링
            records = dailyRecordRepository.findByDateOrderByDateDesc(timestamp);
        } else if (videoId != null) {
            // videoId로만 필터링
            records = dailyRecordRepository.findByVideoId(videoId);
        } else {
            // 날짜와 videoId 모두 없으면 모든 레코드 반환
            records = dailyRecordRepository.findAll();
        }

        return ResponseEntity.ok(records.stream().map(DailyRecordResponseDTO::new).toList());
    }
    // Batch된 결과 없이 수동으로 데이터 불러오는 작업
    public ResponseEntity<List<DailySummaryResponseDTO>> getDailySummaryByDate(String dateString, UserDetailsImpl userDetails) {
        LocalDateTime date;
        try {
            // 입력받은 문자열을 LocalDateTime으로 변환 (yyyy-MM-dd 형식)
            if (dateString != null) {
                date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
            } else {
                // date가 null인 경우 오늘 날짜로 설정
                date = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
            }
        } catch (DateTimeParseException e) {
            // 형식이 잘못된 경우 기본값 (오늘 날짜)
            date = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        }

        List<Video> userVideos = userDetails.getUser().getVideos();
        List<DailySummaryResponseDTO> responseDTOList = new ArrayList<>();

        for (Video video : userVideos) {
            long videoDailyViewCount = 0L;
            long videoWeeklyViewCount = 0L;
            long videoMonthlyViewCount = 0L;
            long videoDailyProfit = 0L;
            long videoWeeklyProfit = 0L;
            long videoMonthlyProfit = 0L;
            long adDailyViewCount = 0L;
            long adWeeklyViewCount = 0L;
            long adMonthlyViewCount = 0L;
            long adDailyProfit = 0L;
            long adWeeklyProfit = 0L;
            long adMonthlyProfit = 0L;

            // 비디오 기준으로 DailyRecord에서 오늘 정보 조회
            Optional<DailyRecord> todayRecordOptional  = dailyRecordRepository.findByDateAndVideo(Timestamp.valueOf(date), video);
            if (todayRecordOptional.isPresent()) {
                DailyRecord todayRecord = todayRecordOptional.get();
                // 어제 정보 조회
                LocalDate yesterday = date.minusDays(1).toLocalDate();
                Optional<DailyRecord> yesterdayRecordOptional = dailyRecordRepository.findByDateAndVideo(Timestamp.valueOf(yesterday.atStartOfDay()), video);

                // 어제 날짜로 하루 빼서 조회
                if (yesterdayRecordOptional.isPresent()) {
                    DailyRecord yesterdayRecord = yesterdayRecordOptional.get();
                    // 어제와의 차이를 계산
                    videoDailyViewCount = todayRecord.getTotalVideoViews() - yesterdayRecord.getTotalVideoViews();
                    adDailyViewCount = todayRecord.getTotalAdViews() - yesterdayRecord.getTotalAdViews();
                    // 예시로 광고 수익과 비디오 수익 계산 (수익 계산 로직은 필요에 따라 추가)
                    videoDailyProfit = getVideoProfit(videoDailyViewCount);
                    adDailyProfit = getAdProfit(adDailyViewCount);
                }

                // 주간 기록 조회 (일주일 전)
                LocalDate weekAgo = date.minusWeeks(1).toLocalDate();
                Optional<DailyRecord> weeklyRecordOptional = dailyRecordRepository.findByDateAndVideo(Timestamp.valueOf(weekAgo.atStartOfDay()), video);
                if (weeklyRecordOptional.isPresent()) {
                    DailyRecord weeklyRecord = weeklyRecordOptional.get();
                    // 주간 기록 차이 계산
                    videoWeeklyViewCount = todayRecord.getTotalVideoViews() - weeklyRecord.getTotalVideoViews();
                    adWeeklyViewCount = todayRecord.getTotalAdViews() - weeklyRecord.getTotalAdViews();
                    videoWeeklyProfit = getVideoProfit(videoWeeklyViewCount);
                    adWeeklyProfit = getAdProfit(adWeeklyViewCount);
                }

                // 월간 기록 조회 (한 달 전)
                LocalDate monthAgo = date.minusMonths(1).toLocalDate();
                Optional<DailyRecord> monthlyRecordOptional = dailyRecordRepository.findByDateAndVideo(Timestamp.valueOf(monthAgo.atStartOfDay()), video);
                if (monthlyRecordOptional.isPresent()) {
                    DailyRecord monthlyRecord = monthlyRecordOptional.get();
                    // 월간 기록 차이 계산
                    videoMonthlyViewCount = todayRecord.getTotalVideoViews() - monthlyRecord.getTotalVideoViews();
                    adMonthlyViewCount = todayRecord.getTotalAdViews() - monthlyRecord.getTotalAdViews();
                    videoMonthlyProfit = getVideoProfit(videoMonthlyViewCount);
                    adMonthlyProfit = getAdProfit(adMonthlyViewCount);
                }
            }

            // 결과 DTO 반환
            responseDTOList.add(new DailySummaryResponseDTO(date,video,
                    videoDailyViewCount, videoWeeklyViewCount, videoMonthlyViewCount,
                    videoDailyProfit, videoWeeklyProfit, videoMonthlyProfit,
                    adDailyViewCount, adWeeklyViewCount, adMonthlyViewCount,
                    adDailyProfit, adWeeklyProfit, adMonthlyProfit));
        }
        return ResponseEntity.ok(responseDTOList);
    }

    // Batch된 결과 없이 수동으로 데이터 불러오는 작업
    public ResponseEntity<List<DailySummaryResponseDTO>> getDailySummaryByDateFromTable(String dateString, UserDetailsImpl userDetails) {
        LocalDateTime date;
        try {
            // 입력받은 문자열을 LocalDateTime으로 변환 (yyyy-MM-dd 형식)
            if (dateString != null) {
                date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
            } else {
                // date가 null인 경우 오늘 날짜로 설정
                date = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
            }
        } catch (DateTimeParseException e) {
            // 형식이 잘못된 경우 기본값 (오늘 날짜)
            date = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        }
        List<Video> userVideos = userDetails.getUser().getVideos();
        LocalDateTime finalDate = date;
        List<DailySummary> summaries = userVideos.stream().flatMap(video -> dailySummaryRepository.findAllByVideoAndDate(video, Timestamp.valueOf(finalDate)).stream()).toList();
        return ResponseEntity.ok(summaries.stream().map(DailySummaryResponseDTO::new).toList());
    }

    // Batch - 일일 영상과 광고 대상 조회수 총합을 기록하는 작업
    @Transactional
    public void setDailyRecord(){
        LocalDateTime todayStart = LocalDate.now().atStartOfDay();  // 오늘 0시 0분 0초
        List<Video> videos = videoRepository.findAll();

        // 모든 비디오 대상
        for (Video video : videos) {
            // 영상 조회수
            Long videoViews = video.getViewCount();
            // 영상의 광고 조회수 총합
            Long adViews = video.getTotalAdViewCount();
            // 이미 있는 기록이라면 덮어쓰기
            Optional<DailyRecord> existingRecord = dailyRecordRepository.findByDateAndVideo(Timestamp.valueOf(todayStart), video);
            if (existingRecord.isPresent()) {
                // 존재하는 경우 업데이트
                existingRecord.get().update(videoViews,adViews);
            } else {
                // 존재하지 않는 경우 새로 생성
                DailyRecord dailyRecord = new DailyRecord(Timestamp.valueOf(todayStart), video,videoViews,adViews);
                dailyRecordRepository.save(dailyRecord);
            }
        }
        ResponseEntity.ok("설정완료");
    }
    
    // Batch - 일간, 주간, 월간 영상과 광고의 조회수 변화량과 이에 따른 수익을 정산하여 데이터베이스에 저장하는 기능
    @Async
    @Transactional
    public void setDailySummaryAsync() {
        LocalDateTime todayStart = LocalDate.now().atStartOfDay();  // 오늘 0시 0분 0초
        LocalDateTime yesterdayStart = todayStart.minusDays(1);  // 어제 0시 0분 0초
        LocalDateTime weekStart = todayStart.minusWeeks(1);  // 1주일 전 (저번주 시작)
        LocalDateTime monthStart = todayStart.minusMonths(1);  // 한 달 전 (지난달 시작)
        List<Video> videos = videoRepository.findAll();
        // 모든 비디오 대상
        for (Video video : videos) {
            long videoDailyViewCount = 0L;
            long videoWeeklyViewCount = 0L;
            long videoMonthlyViewCount = 0L;
            long videoDailyProfit = 0L;
            long videoWeeklyProfit = 0L;
            long videoMonthlyProfit = 0L;
            long adDailyViewCount = 0L;
            long adWeeklyViewCount = 0L;
            long adMonthlyViewCount = 0L;
            long adDailyProfit = 0L;
            long adWeeklyProfit = 0L;
            long adMonthlyProfit = 0L;

            Optional<DailyRecord> todayRecordOptional = dailyRecordRepository.findByDateAndVideo(Timestamp.valueOf(todayStart), video);
            Optional<DailyRecord> yesterdayRecordOptional = dailyRecordRepository.findByDateAndVideo(Timestamp.valueOf(yesterdayStart), video);
            if (todayRecordOptional.isPresent()) {
                DailyRecord todayRecord = todayRecordOptional.get();
                if (yesterdayRecordOptional.isPresent()) {
                    DailyRecord yesterdayRecord = yesterdayRecordOptional.get();
                    videoDailyViewCount = todayRecord.getTotalVideoViews() - yesterdayRecord.getTotalVideoViews();
                    adDailyViewCount = todayRecord.getTotalAdViews() - yesterdayRecord.getTotalAdViews();
                }

                Optional<DailyRecord> prevWeekRecord = dailyRecordRepository.findByDateAndVideo(Timestamp.valueOf(weekStart), video);
                if (prevWeekRecord.isPresent()) {
                    DailyRecord prevRecord = prevWeekRecord.get();
                    videoWeeklyViewCount = todayRecord.getTotalVideoViews() - prevRecord.getTotalVideoViews();
                    adWeeklyViewCount = todayRecord.getTotalAdViews() - prevRecord.getTotalAdViews();
                }

                Optional<DailyRecord> prevMonthRecord  = dailyRecordRepository.findByDateAndVideo(Timestamp.valueOf(monthStart), video);
                if (prevMonthRecord.isPresent()) {
                    DailyRecord prevMonthRecordValue = prevMonthRecord.get();
                    videoMonthlyViewCount = todayRecord.getTotalVideoViews() - prevMonthRecordValue.getTotalVideoViews();
                    adMonthlyViewCount = todayRecord.getTotalAdViews() - prevMonthRecordValue.getTotalAdViews();
                }
            }

            // 이미 존재하는 정산이면 업데이트
            Optional<DailySummary> existingSummary = dailySummaryRepository.findByDateAndVideo(Timestamp.valueOf(todayStart), video);
            if (existingSummary.isPresent()) {
                DailySummary summary = existingSummary.get();
                summary.setVideoDailyViewCount(videoDailyViewCount);
                summary.setVideoWeeklyViewCount(videoWeeklyViewCount);
                summary.setVideoMonthlyViewCount(videoMonthlyViewCount);
                summary.setVideoDailyProfit(videoDailyProfit);
                summary.setVideoWeeklyProfit(videoWeeklyProfit);
                summary.setVideoMonthlyProfit(videoMonthlyProfit);
                summary.setAdDailyViewCount(adDailyViewCount);
                summary.setAdWeeklyViewCount(adWeeklyViewCount);
                summary.setAdMonthlyViewCount(adMonthlyViewCount);
                summary.setAdDailyProfit(adDailyProfit);
                summary.setAdWeeklyProfit(adWeeklyProfit);
                summary.setAdMonthlyProfit(adMonthlyProfit);
            } else {
                // 새로운 정산 기록을 추가
                DailySummary newSummary = new DailySummary(Timestamp.valueOf(todayStart), video,
                        videoDailyViewCount, videoWeeklyViewCount, videoMonthlyViewCount,
                        adDailyViewCount, adWeeklyViewCount, adMonthlyViewCount);
                dailySummaryRepository.save(newSummary);
            }
        }
    }

    public Long getVideoProfit(Long viewCount) {
        double profit = 0.0;  // 수익 변수 초기화
        // 조회수 구간에 따라 수익 계산
        if (viewCount > 1000000) {
            profit += (viewCount - 1000000) * 1.5;
            viewCount = 1000000L;
        }
        if (viewCount > 500000) {
            profit += (viewCount - 500000) * 1.3;
            viewCount = 500000L;
        }
        if (viewCount > 100000) {
            profit += (viewCount - 100000) * 1.1;
            viewCount = 100000L;
        }
        profit += viewCount * 1.0;  // 나머지 조회수에 대한 수익 계산
        // double을 long으로 변환 (절사)
        return (long) profit;
    }

    public Long getAdProfit(Long viewCount) {
        long profit = 0L;  // 수익 변수 초기화
        // 조회수 구간에 따라 수익 계산
        if (viewCount > 1000000) {
            profit += (viewCount - 1000000) * 20;
            viewCount = 1000000L;
        }
        if (viewCount > 500000) {
            profit += (viewCount - 500000) * 15;
            viewCount = 500000L;
        }
        if (viewCount > 100000) {
            profit += (viewCount - 100000) * 112;
            viewCount = 100000L;
        }
        profit += viewCount * 10;  // 나머지 조회수에 대한 수익 계산
        // double을 long으로 변환 (절사)
        return profit;
    }
}
