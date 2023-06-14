package com.ssafy.discoverkorea.client.hotplace.repository;

import com.ssafy.discoverkorea.client.hotplace.HotPlaceComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HotPlaceCommentRepository extends JpaRepository<HotPlaceComment, Long> {

    @Query("select hpc from HotPlaceComment hpc join fetch hpc.hotPlace hp where hpc.id=:hotPlaceCommentId")
    Optional<HotPlaceComment> findJoinHotPlaceById(@Param("hotPlaceCommentId") Long hotPlaceCommentId);
}
