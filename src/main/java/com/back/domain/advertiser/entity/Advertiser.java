package com.back.domain.advertiser.entity;

import com.back.domain.advertiser.dto.AdvertiserCreateReq;
import com.back.domain.advertiser.dto.AdvertiserUpdateReq;
import com.back.global.jpa.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "advertiser")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Advertiser extends BaseEntity {

  private static final String DEFAULT_STATUS = "ACTIVE";

  @Column(nullable = false)
  private String name;

  @Column(name = "business_number", nullable = false, unique = true)
  private String businessNumber;

  @Column(name = "contact_name", nullable = false)
  private String contactName;

  @Column(name = "contact_email", nullable = false)
  private String contactEmail;

  @Column(nullable = false)
  private String status;

  /**
   * 광고주 등록 요청 DTO를 광고주 엔티티로 변환합니다.
   * <p><b>실행 로직:</b><br>
   * 1. 요청 DTO에서 회사명, 사업자 번호, 담당자명, 담당자 이메일을 읽어옵니다. <br>
   * 2. 광고주 기본 상태값을 {@code ACTIVE}로 설정합니다. <br>
   * 3. 빌더를 사용해 광고주 엔티티를 생성합니다.
   *
   * @param req 광고주 등록 요청 DTO
   * @return 생성된 광고주 엔티티
   */
  public static Advertiser from(AdvertiserCreateReq req) {
    return Advertiser.builder()
        .name(req.name())
        .businessNumber(req.businessNumber())
        .contactName(req.contactName())
        .contactEmail(req.contactEmail())
        .status(DEFAULT_STATUS)
        .build();
  }

  /**
   * 광고주 정보를 수정합니다.
   * <p><b>실행 로직:</b><br>
   * 1. 요청 DTO의 회사명, 담당자명, 담당자 이메일, 상태값을 읽어옵니다. <br>
   * 2. 현재 광고주 엔티티의 필드를 새로운 값으로 변경합니다.
   *
   * @param req 광고주 수정 요청 DTO
   */
  public void update(AdvertiserUpdateReq req) {
    this.name = req.name();
    this.contactName = req.contactName();
    this.contactEmail = req.contactEmail();
    this.status = req.status();
  }
}
