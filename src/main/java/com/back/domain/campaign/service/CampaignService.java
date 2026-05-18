package com.back.domain.campaign.service;

import com.back.domain.advertiser.entity.Advertiser;
import com.back.domain.advertiser.repository.AdvertiserRepository;
import com.back.domain.campaign.dto.CampaignReq;
import com.back.domain.campaign.dto.CampaignRes;
import com.back.domain.campaign.entity.Campaign;
import com.back.domain.campaign.repository.CampaignRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CampaignService {

  private final CampaignRepository campaignRepository;
  private final AdvertiserRepository advertiserRepository;

  /**
   * 캠페인을 저장합니다.
   * <p><b>실행 로직:</b><br>
   * 1. 요청받은 광고주 ID로 광고주 엔티티를 조회합니다. <br>
   * 2. 광고주가 존재하지 않으면 {@code ResponseStatusException}을 발생시킵니다. <br>
   * 3. 광고주와 요청 DTO를 이용해 캠페인 엔티티를 생성하고 저장한 뒤 응답 DTO로 반환합니다.
   *
   * @param req 캠페인 등록 요청 DTO
   * @return 저장된 캠페인 응답 DTO
   */
  @Transactional
  public CampaignRes create(CampaignReq req) {
    Advertiser advertiser = advertiserRepository.findById(req.advertiserId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "광고주를 찾을 수 없습니다."));

    Campaign savedCampaign = campaignRepository.save(Campaign.from(advertiser, req));
    return CampaignRes.from(savedCampaign);
  }

  /**
   * 전체 캠페인 목록을 조회합니다.
   * <p><b>실행 로직:</b><br>
   * 1. 저장소에서 전체 캠페인 엔티티를 조회합니다. <br>
   * 2. 각 엔티티를 응답 DTO로 변환합니다. <br>
   * 3. 변환된 목록을 반환합니다.
   *
   * @return 전체 캠페인 응답 목록
   */
  public List<CampaignRes> getCampaigns() {
    return campaignRepository.findAll().stream()
        .map(CampaignRes::from)
        .toList();
  }

  /**
   * 특정 캠페인을 조회합니다.
   * <p><b>실행 로직:</b><br>
   * 1. 전달받은 캠페인 ID로 엔티티를 조회합니다. <br>
   * 2. 캠페인이 존재하지 않으면 {@code ResponseStatusException}을 발생시킵니다. <br>
   * 3. 조회된 엔티티를 응답 DTO로 변환하여 반환합니다.
   *
   * @param campaignId 조회할 캠페인 ID
   * @return 조회된 캠페인 응답 DTO
   */
  public CampaignRes getCampaign(Long campaignId) {
    return CampaignRes.from(findCampaignById(campaignId));
  }

  /**
   * 캠페인 ID로 캠페인 엔티티를 조회합니다.
   * <p><b>실행 로직:</b><br>
   * 1. 저장소에서 캠페인 ID로 엔티티를 조회합니다. <br>
   * 2. 조회 결과가 없으면 404 예외를 발생시킵니다. <br>
   * 3. 조회된 캠페인 엔티티를 반환합니다.
   *
   * @param campaignId 조회할 캠페인 ID
   * @return 조회된 캠페인 엔티티
   */
  private Campaign findCampaignById(Long campaignId) {
    return campaignRepository.findById(campaignId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "캠페인을 찾을 수 없습니다."));
  }
}
