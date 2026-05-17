package com.back.domain.advertiser.dto;

import com.back.domain.advertiser.entity.Advertiser;
import java.time.LocalDateTime;

public record AdvertiserRes(
    Long id,
    String name,
    String businessNumber,
    String contactName,
    String contactEmail,
    String status,
    LocalDateTime createDate,
    LocalDateTime modifyDate
) {

  public static AdvertiserRes from(Advertiser advertiser) {
    return new AdvertiserRes(
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
