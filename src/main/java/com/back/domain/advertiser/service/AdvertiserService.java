package com.back.domain.advertiser.service;

import com.back.domain.advertiser.dto.AdvertiserCreateReq;
import com.back.domain.advertiser.dto.AdvertiserRes;
import com.back.domain.advertiser.dto.AdvertiserUpdateReq;
import com.back.domain.advertiser.entity.Advertiser;
import com.back.domain.advertiser.repository.AdvertiserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdvertiserService {

  private final AdvertiserRepository advertiserRepository;

  /**
   * 광고주를 저장합니다.
   * <p><b>실행 로직:</b><br>
   * 1. 요청받은 사업자 번호가 이미 등록되어 있는지 확인합니다. <br>
   * 2. 중복이 아니라면 요청 DTO를 광고주 엔티티로 변환합니다. <br>
   * 3. 광고주를 저장하고 응답 DTO로 변환하여 반환합니다.
   *
   * @param req 광고주 등록 요청 DTO
   * @return 저장된 광고주 응답 DTO
   */
  @Transactional
  public AdvertiserRes create(AdvertiserCreateReq req) {
    validateDuplicateBusinessNumber(req.businessNumber());

    Advertiser advertiser = Advertiser.from(req);
    Advertiser savedAdvertiser = advertiserRepository.save(advertiser);

    return AdvertiserRes.from(savedAdvertiser);
  }

  /**
   * 전체 광고주 목록을 조회합니다.
   * <p><b>실행 로직:</b><br>
   * 1. 저장소에서 전체 광고주 엔티티를 조회합니다. <br>
   * 2. 각 엔티티를 응답 DTO로 변환합니다. <br>
   * 3. 변환된 목록을 반환합니다.
   *
   * @return 전체 광고주 응답 목록
   */
  public List<AdvertiserRes> getAll() {
    return advertiserRepository.findAll().stream()
        .map(AdvertiserRes::from)
        .toList();
  }

  /**
   * 특정 광고주를 조회합니다.
   * <p><b>실행 로직:</b><br>
   * 1. 전달받은 광고주 ID로 엔티티를 조회합니다. <br>
   * 2. 광고주가 존재하지 않으면 {@code ResponseStatusException}을 발생시킵니다. <br>
   * 3. 조회된 엔티티를 응답 DTO로 변환하여 반환합니다.
   *
   * @param advertiserId 조회할 광고주 ID
   * @return 조회된 광고주 응답 DTO
   */
  public AdvertiserRes getById(Long advertiserId) {
    return AdvertiserRes.from(findById(advertiserId));
  }

  /**
   * 특정 광고주 정보를 수정합니다.
   * <p><b>실행 로직:</b><br>
   * 1. 광고주 ID로 수정 대상 엔티티를 조회합니다. <br>
   * 2. 대상 광고주가 존재하지 않으면 {@code ResponseStatusException}을 발생시킵니다. <br>
   * 3. 요청값으로 엔티티를 수정하고, 수정된 결과를 응답 DTO로 반환합니다.
   *
   * @param advertiserId 수정할 광고주 ID
   * @param req 광고주 수정 요청 DTO
   * @return 수정된 광고주 응답 DTO
   */
  @Transactional
  public AdvertiserRes update(Long advertiserId, AdvertiserUpdateReq req) {
    Advertiser advertiser = findById(advertiserId);
    advertiser.update(req);

    return AdvertiserRes.from(advertiser);
  }

  /**
   * 광고주 ID로 광고주 엔티티를 조회합니다.
   * <p><b>실행 로직:</b><br>
   * 1. 저장소에서 광고주 ID로 엔티티를 조회합니다. <br>
   * 2. 조회 결과가 없으면 404 예외를 발생시킵니다. <br>
   * 3. 조회된 광고주 엔티티를 반환합니다.
   *
   * @param advertiserId 조회할 광고주 ID
   * @return 조회된 광고주 엔티티
   */
  private Advertiser findById(Long advertiserId) {
    return advertiserRepository.findById(advertiserId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "광고주를 찾을 수 없습니다."));
  }

  /**
   * 사업자 번호 중복 여부를 검증합니다.
   * <p><b>실행 로직:</b><br>
   * 1. 저장소에서 사업자 번호 존재 여부를 조회합니다. <br>
   * 2. 이미 등록된 사업자 번호라면 409 예외를 발생시킵니다.
   *
   * @param businessNumber 검증할 사업자 번호
   */
  private void validateDuplicateBusinessNumber(String businessNumber) {
    if (advertiserRepository.existsByBusinessNumber(businessNumber)) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 등록된 사업자 번호입니다.");
    }
  }
}
