package com.back.domain.advertiser.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AdvertiserCreateReq(
    @NotBlank(message = "회사명은 필수 입력 항목입니다.")
    String name,

    @NotBlank(message = "사업자 번호는 필수 입력 항목입니다.")
    String businessNumber,

    @NotBlank(message = "담당자명은 필수 입력 항목입니다.")
    String contactName,

    @NotBlank(message = "담당자 이메일은 필수 입력 항목입니다.")
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    String contactEmail
) {
}
