package com.back.domain.advertiser.dto;

import com.back.domain.advertiser.entity.Advertiser;
import java.time.LocalDateTime;

public record AdvertiserResponse(
    Long id,
    String name,
    String businessNumber,
    String contactName,
    String contactEmail,
    String status,
    LocalDateTime createDate,
    LocalDateTime modifyDate
) {

  public static AdvertiserResponse from(Advertiser advertiser) {
    return new AdvertiserResponse(
        advertiser.getId(),
        advertiser.getName(),
        advertiser.getBusinessNumber(),
        advertiser.getContactName(),
        advertiser.getContactEmail(),
        advertiser.getStatus(),
        advertiser.getCreateDate(),
        advertiser.getModifyDate()
    );
  }
}
