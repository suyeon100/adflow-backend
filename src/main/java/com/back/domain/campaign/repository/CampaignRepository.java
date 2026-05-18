package com.back.domain.campaign.repository;

import com.back.domain.campaign.entity.Campaign;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 캠페인 엔티티에 대한 데이터 접근을 담당하는 저장소입니다.
 */
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
  @Query("select c from Campaign c join fetch c.advertiser")
  List<Campaign> findAllWithAdvertiser();

  @Query("select c from Campaign c join fetch c.advertiser where c.id = :campaignId")
  Optional<Campaign> findByIdWithAdvertiser(Long campaignId);
}
