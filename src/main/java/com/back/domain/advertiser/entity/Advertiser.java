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

  public static Advertiser from(AdvertiserCreateReq req) {
    return Advertiser.builder()
        .name(req.name())
        .businessNumber(req.businessNumber())
        .contactName(req.contactName())
        .contactEmail(req.contactEmail())
        .status(DEFAULT_STATUS)
        .build();
  }

  public void update(AdvertiserUpdateReq req) {
    this.name = req.name();
    this.contactName = req.contactName();
    this.contactEmail = req.contactEmail();
    this.status = req.status();
  }
}
