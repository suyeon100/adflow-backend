package com.back.domain.campaign.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * 캠페인 등록 요청 DTO입니다.
 *
 * @param name 캠페인명
 * @param objective 캠페인 목적
 * @param startDate 캠페인 시작일
 * @param endDate 캠페인 종료일
 * @param budget 캠페인 예산
 */
public record CampaignCreateReq(

        @NotBlank(message = "캠페인명은 필수 입력 항목입니다.")
        String name,

        @NotBlank(message = "캠페인 목적은 필수 입력 항목입니다.")
        String objective,

        @NotNull(message = "시작일은 필수 입력 항목입니다.")
        LocalDate startDate,

        @NotNull(message = "종료일은 필수 입력 항목입니다.")
        LocalDate endDate,

        @NotNull(message = "예산은 필수 입력 항목입니다.")
        Long budget

) {
}
