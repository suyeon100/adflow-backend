package com.back.domain.campaign.repository;

import com.back.domain.campaign.entity.Campaign;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 캠페인 엔티티에 대한 데이터 접근을 담당하는 저장소입니다.
 */
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

  @Override
  @EntityGraph(attributePaths = "advertiser")
  List<Campaign> findAll();
}
