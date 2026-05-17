package com.back.domain.advertiser.repository;

import com.back.domain.advertiser.entity.Advertiser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 광고주 엔티티에 대한 데이터 접근을 담당하는 저장소입니다.
 */
public interface AdvertiserRepository extends JpaRepository<Advertiser, Long> {

  /**
   * 사업자 번호로 광고주 존재 여부를 조회합니다.
   *
   * @param businessNumber 조회할 사업자 번호
   * @return 존재하면 {@code true}, 존재하지 않으면 {@code false}
   */
  boolean existsByBusinessNumber(String businessNumber);
}
