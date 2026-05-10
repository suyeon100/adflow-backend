package com.back.domain.advertiser.dto;

import jakarta.validation.constraints.NotBlank;

public record AdvertiserCreateReq(
    @NotBlank(message = "회사명은 필수 입력 항목입니다.")
    String name,

    @NotBlank(message = "사업자 번호는 필수 입력 항목입니다.")
    String businessNumber
) {
}
