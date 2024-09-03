package com.sparta.service;

import com.sparta.dto.DailyRecordResponseDTO;
import com.sparta.dto.TopLengthResponseDTO;
import com.sparta.dto.TopViewResponseDTO;
import com.sparta.entity.*;
import com.sparta.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdjustService {

    private final VideoRepository videoRepository;
    private final DailyRecordRepository dailyRecordRepository;
    private final HistoryRepository historyRepository;
    private final AdListRepository adListRepository;
    private final TopViewDailyRepository topViewDailyRepository;
    private final TopViewWeeklyRepository topViewWeeklyRepository;
    private final TopViewMonthlyRepository topViewMonthlyRepository;
    private final TopLengthDailyRepository topLengthDailyRepository;
    private final TopLengthWeeklyRepository topLengthWeeklyRepository;
    private final TopLengthMonthlyRepository topLengthMonthlyRepository;

    public ResponseEntity<List<DailyRecordResponseDTO>> getDailyRecord(){
        return ResponseEntity.ok(dailyRecordRepository.findAll().stream().map(DailyRecordResponseDTO::new).collect(Collectors.toList()));
    }

    @Transactional
    public void setDailyRecord(){
        LocalDateTime todayStart = LocalDate.now().atStartOfDay();  // 오늘 0시 0분 0초
        List<Video> videos = videoRepository.findAll();
        for (Video video : videos) {
            // 현재 조회수
            Long todayViews = video.getViewCount();
            // 현재 시청 시간 합계
            Long watchedLength = historyRepository.sumWatchedLengthByVideo(video);
            // 영상 수익
            Long profit_video = video.getProfit();
            // 광고 수익
            Long profit_ad = adListRepository.findByVideo(video).stream().mapToLong(AdList::getProfit).sum();

            Optional<DailyRecord> existingRecord = dailyRecordRepository.findByDateAndVideo(Timestamp.valueOf(todayStart), video);
            if (existingRecord.isPresent()) {
                // 존재하는 경우 업데이트
                existingRecord.get().update(todayViews,watchedLength,profit_video,profit_ad);
            } else {
                // 존재하지 않는 경우 새로 생성
                DailyRecord dailyRecord = new DailyRecord(Timestamp.valueOf(todayStart), video, todayViews, watchedLength,profit_video,profit_ad);
                dailyRecordRepository.save(dailyRecord);
            }
        }
        ResponseEntity.ok("설정완료");
    }

    @Transactional
    public void setDailyTop(){
        LocalDateTime todayStart = LocalDate.now().atStartOfDay().minusDays(1);  //오늘
        List<DailyRecord> startRecord = dailyRecordRepository.findByDate(Timestamp.valueOf(todayStart)); // 오늘 기록
        List<DailyRecord> endRecord = dailyRecordRepository.findByDate(Timestamp.valueOf(todayStart.minusDays(1))); // 어제 기록

        // 어제 기록을 map으로 변환
        Map<Long, Long> endViewsMap = endRecord.stream().collect(Collectors.toMap(record -> record.getVideo().getId(), DailyRecord::getViews));
        Map<Long, Long> endLengthMap = endRecord.stream().collect(Collectors.toMap(record -> record.getVideo().getId(), DailyRecord::getLength));

        // 오늘 기록별로 차이 계산 후 TopViewDaily에 설정함
        List<TopViewDaily> topViewDailies = startRecord.stream().map(record -> {
                    Long yesterdayViews = endViewsMap.getOrDefault(record.getVideo().getId(), 0L);
                    Long viewDifference = record.getViews() - yesterdayViews;
                    Video video = record.getVideo();
                    return new TopViewDaily(Timestamp.valueOf(todayStart),viewDifference,video);
                }).sorted(Comparator.comparingLong(TopViewDaily::getViews).reversed()).limit(5).toList();

        topViewDailyRepository.deleteByDate(Timestamp.valueOf(todayStart));
        int rank = 1;
        for (TopViewDaily topViewDaily : topViewDailies) {
            topViewDaily.setRanking(rank++);
            topViewDailyRepository.save(topViewDaily);
        }

        // 오늘 기록별로 차이 계산 후 TopLengthDaily에 설정함
        List<TopLengthDaily> topLengthDailies = startRecord.stream().map(record -> {
                    Long yesterdayLength = endLengthMap.getOrDefault(record.getVideo().getId(), 0L);
                    Long lengthDifference = record.getLength() - yesterdayLength;
                    Video video = record.getVideo();
                    return new TopLengthDaily(Timestamp.valueOf(todayStart),lengthDifference,video);
                }).sorted(Comparator.comparingLong(TopLengthDaily::getLength).reversed()).limit(5).toList();

        topLengthDailyRepository.deleteByDate(Timestamp.valueOf(todayStart));
        rank = 1;
        for (TopLengthDaily topLengthDaily : topLengthDailies) {
            topLengthDaily.setRanking(rank++);
            topLengthDailyRepository.save(topLengthDaily);
        }
        ResponseEntity.ok("일별 TOP5 기록 완료");
    }

    @Transactional
    public void setWeeklyTop(){
        LocalDateTime todayStart = LocalDate.now().atStartOfDay().minusDays(1);  //오늘
        LocalDateTime startOfWeek = todayStart.with(ChronoField.DAY_OF_WEEK, 1); // 월요일
        List<DailyRecord> startRecord = dailyRecordRepository.findByDate(Timestamp.valueOf(todayStart)); // 오늘 기록
        List<DailyRecord> endRecord = dailyRecordRepository.findByDate(Timestamp.valueOf(startOfWeek)); // 어제 기록

        // 어제 기록을 map으로 변환
        Map<Long, Long> endViewsMap = endRecord.stream().collect(Collectors.toMap(record -> record.getVideo().getId(), DailyRecord::getViews));
        Map<Long, Long> endLengthMap = endRecord.stream().collect(Collectors.toMap(record -> record.getVideo().getId(), DailyRecord::getLength));

        boolean isStartOfWeek = todayStart.isEqual(startOfWeek);

        // 오늘이 주의 첫 번째 날인 경우 차이 계산 없이 데이터 추가
        List<TopViewWeekly> topViewDailies = startRecord.stream().map(record -> {
            Long viewDifference = isStartOfWeek ? record.getViews() : record.getViews() - endViewsMap.getOrDefault(record.getVideo().getId(), 0L);
            return new TopViewWeekly(Timestamp.valueOf(todayStart), viewDifference, record.getVideo());
        }).sorted(Comparator.comparingLong(TopViewWeekly::getViews).reversed()).limit(5).toList();

        // 오늘이 주의 첫 번째 날인 경우 차이 계산 없이 데이터 추가
        List<TopLengthWeekly> topLengthDailies = startRecord.stream().map(record -> {
            Long lengthDifference = isStartOfWeek ? record.getLength() : record.getLength() - endLengthMap.getOrDefault(record.getVideo().getId(), 0L);
            return new TopLengthWeekly(Timestamp.valueOf(todayStart),lengthDifference,record.getVideo());
        }).sorted(Comparator.comparingLong(TopLengthWeekly::getLength).reversed()).limit(5).toList();

        topViewWeeklyRepository.deleteByDate(Timestamp.valueOf(todayStart));
        topLengthWeeklyRepository.deleteByDate(Timestamp.valueOf(todayStart));

        // 순위 설정 및 저장
        int rank = 1;
        for (TopViewWeekly topViewWeekly : topViewDailies) {
            topViewWeekly.setRanking(rank++);
            topViewWeeklyRepository.save(topViewWeekly);
        }

        rank = 1;
        for (TopLengthWeekly topLengthWeekly : topLengthDailies) {
            topLengthWeekly.setRanking(rank++);
            topLengthWeeklyRepository.save(topLengthWeekly);
        }
    }

    @Transactional
    public void setMonthlyTop() {
        LocalDateTime todayStart = LocalDate.now().atStartOfDay().minusDays(1);  // 오늘
        LocalDateTime startOfMonth = todayStart.withDayOfMonth(1); // 월의 첫 날
        List<DailyRecord> startRecord = dailyRecordRepository.findByDate(Timestamp.valueOf(todayStart)); // 오늘 기록
        List<DailyRecord> endRecord = dailyRecordRepository.findByDate(Timestamp.valueOf(startOfMonth)); // 월의 첫 날 기록

        // 월의 첫 날 기록을 map으로 변환
        Map<Long, Long> endViewsMap = endRecord.stream().collect(Collectors.toMap(record -> record.getVideo().getId(), DailyRecord::getViews));
        Map<Long, Long> endLengthMap = endRecord.stream().collect(Collectors.toMap(record -> record.getVideo().getId(), DailyRecord::getLength));

        boolean isStartOfMonth = todayStart.isEqual(startOfMonth);

        // 오늘이 월의 첫 날인 경우 차이 계산 없이 데이터 추가
        List<TopViewMonthly> topViewDailies = startRecord.stream().map(record -> {
            Long viewDifference = isStartOfMonth ? record.getViews() : record.getViews() - endViewsMap.getOrDefault(record.getVideo().getId(), 0L);
            return new TopViewMonthly(Timestamp.valueOf(todayStart), viewDifference, record.getVideo());
        }).sorted(Comparator.comparingLong(TopViewMonthly::getViews).reversed()).limit(5).toList();

        // 오늘이 월의 첫 날인 경우 차이 계산 없이 데이터 추가
        List<TopLengthMonthly> topLengthDailies = startRecord.stream().map(record -> {
            Long lengthDifference = isStartOfMonth ? record.getLength() : record.getLength() - endLengthMap.getOrDefault(record.getVideo().getId(), 0L);
            return new TopLengthMonthly(Timestamp.valueOf(todayStart), lengthDifference, record.getVideo());
        }).sorted(Comparator.comparingLong(TopLengthMonthly::getLength).reversed()).limit(5).toList();

        // 저장 전 데이터 삭제
        topViewMonthlyRepository.deleteByDate(Timestamp.valueOf(todayStart));
        topLengthMonthlyRepository.deleteByDate(Timestamp.valueOf(todayStart));

        // 순위 설정 및 저장
        int rank = 1;
        for (TopViewMonthly topViewMonthly : topViewDailies) {
            topViewMonthly.setRanking(rank++);
            topViewMonthlyRepository.save(topViewMonthly);
        }

        rank = 1;
        for (TopLengthMonthly topLengthMonthly : topLengthDailies) {
            topLengthMonthly.setRanking(rank++);
            topLengthMonthlyRepository.save(topLengthMonthly);
        }
    }

    public ResponseEntity<List<TopViewResponseDTO>> getTopView(String option){
        List<TopViewResponseDTO> list = switch (option) {
            case "weekly" -> topViewWeeklyRepository.findAll().stream().map(TopViewResponseDTO::new).toList();
            case "monthly" -> topViewMonthlyRepository.findAll().stream().map(TopViewResponseDTO::new).toList();
            default -> topViewDailyRepository.findAll().stream().map(TopViewResponseDTO::new).toList();
        };
        return ResponseEntity.ok(list);
    }

    public ResponseEntity<List<TopLengthResponseDTO>> getTopLength(String option){
        List<TopLengthResponseDTO> list = switch (option) {
            case "weekly" -> topLengthDailyRepository.findAll().stream().map(TopLengthResponseDTO::new).toList();
            case "monthly" -> topLengthWeeklyRepository.findAll().stream().map(TopLengthResponseDTO::new).toList();
            default -> topLengthMonthlyRepository.findAll().stream().map(TopLengthResponseDTO::new).toList();
        };
        return ResponseEntity.ok(list);
    }
}
