package com.back.domain.campaign.controller;

import com.back.domain.campaign.dto.CampaignReq;
import com.back.domain.campaign.dto.CampaignRes;
import com.back.domain.campaign.dto.CampaignUpdateReq;
import com.back.domain.campaign.service.CampaignService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/campaigns")
public class CampaignController {

  private final CampaignService campaignService;

  /**
   * 캠페인을 등록합니다.
   * <p><b>실행 로직:</b><br>
   * 1. 요청 본문의 필수값을 검증합니다. <br>
   * 2. 서비스 계층으로 등록 요청을 전달하여 광고주 존재 여부를 확인합니다. <br>
   * 3. 검증이 완료되면 캠페인을 저장하고, 저장된 캠페인 정보를 응답으로 반환합니다.
   *
   * @param req 캠페인 등록 요청 DTO
   * @return 등록된 캠페인 응답 DTO
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CampaignRes create(@Valid @RequestBody CampaignReq req) {
    return campaignService.create(req);
  }

  /**
   * 전체 캠페인 목록을 조회합니다.
   * <p><b>실행 로직:</b><br>
   * 1. 서비스 계층에 전체 캠페인 조회를 요청합니다. <br>
   * 2. 저장된 캠페인 엔티티 목록을 응답 DTO 목록으로 변환합니다. <br>
   * 3. 변환된 목록을 클라이언트에 반환합니다.
   *
   * @return 전체 캠페인 응답 목록
   */
  @GetMapping
  public List<CampaignRes> getCampaigns() {
    return campaignService.getCampaigns();
  }

  /**
   * 특정 캠페인을 조회합니다.
   * <p><b>실행 로직:</b><br>
   * 1. 경로 변수로 전달받은 캠페인 ID를 서비스 계층에 전달합니다. <br>
   * 2. 해당 ID의 캠페인이 존재하지 않으면 예외를 발생시킵니다. <br>
   * 3. 조회된 캠페인 정보를 응답 DTO로 반환합니다.
   *
   * @param campaignId 조회할 캠페인 ID
   * @return 조회된 캠페인 응답 DTO
   */
  @GetMapping("/{campaignId}")
  public CampaignRes getCampaign(@PathVariable Long campaignId) {
    return campaignService.getCampaign(campaignId);
  }

  /**
   * 특정 캠페인 정보를 수정합니다.
   * <p><b>실행 로직:</b><br>
   * 1. 경로 변수로 전달받은 캠페인 ID와 요청 본문을 서비스 계층에 전달합니다. <br>
   * 2. 대상 캠페인이 존재하지 않으면 예외를 발생시킵니다. <br>
   * 3. 캠페인 정보와 상태값을 수정한 뒤 결과를 반환합니다.
   *
   * @param campaignId 수정할 캠페인 ID
   * @param req 캠페인 수정 요청 DTO
   * @return 수정된 캠페인 응답 DTO
   */
  @PutMapping("/{campaignId}")
  public CampaignRes update(@PathVariable Long campaignId, @Valid @RequestBody CampaignUpdateReq req) {
    return campaignService.update(campaignId, req);
  }

  /**
   * 특정 캠페인을 삭제합니다.
   * <p><b>실행 로직:</b><br>
   * 1. 경로 변수로 전달받은 캠페인 ID를 서비스 계층에 전달합니다. <br>
   * 2. 대상 캠페인이 존재하지 않으면 예외를 발생시킵니다. <br>
   * 3. 조회된 캠페인을 삭제하고 204 응답을 반환합니다.
   *
   * @param campaignId 삭제할 캠페인 ID
   */
  @DeleteMapping("/{campaignId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long campaignId) {
    campaignService.delete(campaignId);
  }
}
