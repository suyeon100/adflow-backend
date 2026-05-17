package com.back.domain.campaign.dto;

import com.back.domain.campaign.entity.Campaign;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 캠페인 응답 DTO입니다.
 *
 * @param id 캠페인 ID
 * @param advertiserId 광고주 ID
 * @param advertiserName 광고주명
 * @param name 캠페인명
 * @param objective 캠페인 목적
 * @param budget 캠페인 예산
 * @param startDate 캠페인 시작일
 * @param endDate 캠페인 종료일
 * @param status 캠페인 상태값
 * @param createDate 생성일시
 * @param modifyDate 수정일시
 */
public record CampaignRes(

        Long id,
        Long advertiserId,
        String advertiserName,
        String name,
        String objective,
        Long budget,
        LocalDate startDate,
        LocalDate endDate,
        String status,
        LocalDateTime createDate,
        LocalDateTime modifyDate

) {
    /**
     * 캠페인 엔티티를 응답 DTO로 변환합니다.
     * <p><b>실행 로직:</b><br>
     * 1. 캠페인 엔티티의 기본 정보와 광고주 정보를 읽어옵니다. <br>
     * 2. 응답에 필요한 필드만 추출하여 DTO를 생성합니다. <br>
     * 3. 생성된 응답 DTO를 반환합니다.
     *
     * @param campaign 변환할 캠페인 엔티티
     * @return 캠페인 응답 DTO
     */
    public static CampaignRes from(Campaign campaign) {
        return new CampaignRes(
                campaign.getId(),
                campaign.getAdvertiser().getId(),
                campaign.getAdvertiser().getName(),
                campaign.getName(),
                campaign.getObjective(),
                campaign.getBudget(),
                campaign.getStartDate(),
                campaign.getEndDate(),
                campaign.getStatus(),
                campaign.getCreateDate(),
                campaign.getModifyDate()


        );
    }
}
