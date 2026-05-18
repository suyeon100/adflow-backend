package com.back.domain.campaign.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

/**
 * 캠페인 등록 요청 DTO입니다.
 *
 * @param advertiserId 광고주 ID
 * @param name 캠페인명
 * @param objective 캠페인 목적
 * @param startDate 캠페인 시작일
 * @param endDate 캠페인 종료일
 * @param budget 캠페인 예산
 */
public record CampaignCreateReq(
    @NotNull(message = "광고주 ID는 필수 입력 항목입니다.")
    Long advertiserId,

    @NotBlank(message = "캠페인명은 필수 입력 항목입니다.")
    String name,

    @NotBlank(message = "캠페인 목적은 필수 입력 항목입니다.")
    String objective,

    @NotNull(message = "시작일은 필수 입력 항목입니다.")
    LocalDate startDate,

    @NotNull(message = "종료일은 필수 입력 항목입니다.")
    LocalDate endDate,

    @NotNull(message = "예산은 필수 입력 항목입니다.")
    @PositiveOrZero(message = "예산은 0원 이상이어야 합니다.")
    Long budget
) {

  @AssertTrue(message = "시작일은 종료일보다 늦을 수 없습니다.")
  public boolean isValidPeriod() {
    if (startDate == null || endDate == null) {
      return true;
    }
    return !startDate.isAfter(endDate);
  }
}
