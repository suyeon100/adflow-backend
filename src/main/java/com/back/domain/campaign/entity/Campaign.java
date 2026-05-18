package com.back.domain.campaign.entity;

import com.back.domain.advertiser.entity.Advertiser;
import com.back.domain.campaign.dto.CampaignReq;
import com.back.domain.campaign.dto.CampaignUpdateReq;
import com.back.global.jpa.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "campaign")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Campaign extends BaseEntity {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "advertiser_id", nullable = false)
  private Advertiser advertiser;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String objective;

  @Column(nullable = false)
  private Long budget;

  @Column(nullable = false)
  private LocalDate startDate;

  @Column(nullable = false)
  private LocalDate endDate;

  @Builder.Default
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private CampaignStatus status = CampaignStatus.ACTIVE;

  /**
   * 캠페인 등록 요청 DTO를 캠페인 엔티티로 변환합니다.
   * <p><b>실행 로직:</b><br>
   * 1. 전달받은 광고주 엔티티와 캠페인 등록 요청값을 확인합니다. <br>
   * 2. 캠페인명, 목적, 예산, 시작일, 종료일을 엔티티 필드에 매핑합니다. <br>
   * 3. 기본 상태값을 {@code ACTIVE}로 설정한 뒤 캠페인 엔티티를 생성합니다.
   *
   * @param advertiser 캠페인을 소유하는 광고주 엔티티
   * @param req 캠페인 등록 요청 DTO
   * @return 생성된 캠페인 엔티티
   */
  public static Campaign from(Advertiser advertiser, CampaignReq req) {
    return Campaign.builder()
        .advertiser(advertiser)
        .name(req.name())
        .objective(req.objective())
        .budget(req.budget())
        .startDate(req.startDate())
        .endDate(req.endDate())
        .build();
  }

  /**
   * 캠페인 정보를 수정합니다.
   * <p><b>실행 로직:</b><br>
   * 1. 수정 요청 DTO에서 캠페인명, 목적, 예산, 시작일, 종료일, 상태값을 읽어옵니다. <br>
   * 2. 현재 캠페인 엔티티의 필드를 새로운 값으로 변경합니다.
   *
   * @param req 캠페인 수정 요청 DTO
   */
  public void update(CampaignUpdateReq req) {
    this.name = req.name();
    this.objective = req.objective();
    this.budget = req.budget();
    this.startDate = req.startDate();
    this.endDate = req.endDate();
    this.status = req.status();
  }
}
