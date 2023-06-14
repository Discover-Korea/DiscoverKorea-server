package com.ssafy.discoverkorea.client.hotplace.repository;

import com.ssafy.discoverkorea.client.hotplace.HotPlaceScrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HotPlaceScrapRepository extends JpaRepository<HotPlaceScrap, Long> {

    @Query("select hps.id from HotPlaceScrap hps join hps.member m where hps.member.loginId=:loginId and hps.hotPlace.id=:hotPlaceId")
    Optional<Long> findByLoginIdAndHotPlaceId(@Param("loginId") String loginId, @Param("hotPlaceId") Long hotPlaceId);
}
