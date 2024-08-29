package com.sparta.dto;

import com.sparta.entity.AdList;
import com.sparta.entity.History;
import com.sparta.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;

@Getter
public class VideoCreateRequestDTO {

    @NotBlank(message = "영상 업로드는 필수입니다.")
    private String url;

    @NotBlank(message = "썸네일은 필수입니다.")
    private String thumbnail;

    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @NotNull
    private Long duration;

    private List<Long> adIds; // 광고 아이템 목록을 위한 필드 추가
}