package com.ssafy.discoverkorea.client.hotplace.repository;

import com.ssafy.discoverkorea.client.hotplace.HotPlaceLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HotPlaceLikeRepository extends JpaRepository<HotPlaceLike, Long> {

    @Query("select hpl.id from HotPlaceLike hpl join hpl.member m where hpl.member.loginId=:loginId and hpl.hotPlace.id=:hotPlaceId")
    Optional<Long> findByLoginIdAndHotPlaceId(@Param("loginId") String loginId, @Param("hotPlaceId") Long hotPlaceId);
}
