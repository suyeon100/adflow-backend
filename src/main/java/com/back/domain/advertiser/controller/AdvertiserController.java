package com.back.domain.advertiser.controller;

import com.back.domain.advertiser.dto.AdvertiserCreateReq;
import com.back.domain.advertiser.dto.AdvertiserRes;
import com.back.domain.advertiser.dto.AdvertiserUpdateReq;
import com.back.domain.advertiser.service.AdvertiserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/advertisers")
public class AdvertiserController {

  private final AdvertiserService advertiserService;

  /**
   * 광고주를 등록합니다.
   * <p><b>실행 로직:</b><br>
   * 1. 요청 본문의 필수값과 이메일 형식을 검증합니다. <br>
   * 2. 서비스 계층으로 등록 요청을 전달하여 사업자 번호 중복 여부를 확인합니다. <br>
   * 3. 검증이 완료되면 광고주를 저장하고, 저장된 광고주 정보를 응답으로 반환합니다.
   *
   * @param req 광고주 등록 요청 DTO
   * @return 등록된 광고주 응답 DTO
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AdvertiserRes create(@Valid @RequestBody AdvertiserCreateReq req) {
    return advertiserService.create(req);
  }

  /**
   * 전체 광고주 목록을 조회합니다.
   * <p><b>실행 로직:</b><br>
   * 1. 서비스 계층에 전체 광고주 조회를 요청합니다. <br>
   * 2. 저장된 광고주 엔티티 목록을 응답 DTO 목록으로 변환합니다. <br>
   * 3. 변환된 목록을 클라이언트에 반환합니다.
   *
   * @return 전체 광고주 응답 목록
   */
  @GetMapping
  public List<AdvertiserRes> getAll() {
    return advertiserService.getAll();
  }

  /**
   * 특정 광고주를 조회합니다.
   * <p><b>실행 로직:</b><br>
   * 1. 경로 변수로 전달받은 광고주 ID를 서비스 계층에 전달합니다. <br>
   * 2. 해당 ID의 광고주가 존재하지 않으면 예외를 발생시킵니다. <br>
   * 3. 조회된 광고주 정보를 응답 DTO로 반환합니다.
   *
   * @param advertiserId 조회할 광고주 ID
   * @return 조회된 광고주 응답 DTO
   */
  @GetMapping("/{advertiserId}")
  public AdvertiserRes getById(@PathVariable Long advertiserId) {
    return advertiserService.getById(advertiserId);
  }

  /**
   * 특정 광고주 정보를 수정합니다.
   * <p><b>실행 로직:</b><br>
   * 1. 경로 변수로 전달받은 광고주 ID와 요청 본문을 서비스 계층에 전달합니다. <br>
   * 2. 대상 광고주가 존재하지 않으면 예외를 발생시킵니다. <br>
   * 3. 광고주의 회사명, 담당자명, 담당자 이메일, 상태값을 수정한 뒤 결과를 반환합니다.
   *
   * @param advertiserId 수정할 광고주 ID
   * @param req 광고주 수정 요청 DTO
   * @return 수정된 광고주 응답 DTO
   */
  @PutMapping("/{advertiserId}")
  public AdvertiserRes update(
      @PathVariable Long advertiserId,
      @Valid @RequestBody AdvertiserUpdateReq req
  ) {
    return advertiserService.update(advertiserId, req);
  }
}
