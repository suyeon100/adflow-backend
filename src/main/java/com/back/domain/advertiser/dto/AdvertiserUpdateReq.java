package com.back.domain.advertiser.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AdvertiserUpdateReq(
    @NotBlank(message = "회사명은 필수 입력 항목입니다.")
    String name,

    @NotBlank(message = "담당자명은 필수 입력 항목입니다.")
    String contactName,

    @NotBlank(message = "담당자 이메일은 필수 입력 항목입니다.")
    @Email(message = "올바른 이메일 형식이어야 합니다.")
    String contactEmail,

    @NotBlank(message = "상태값은 필수 입력 항목입니다.")
    String status
) {
}
