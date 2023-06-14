package com.ssafy.discoverkorea.client.hotplace.repository;

import com.ssafy.discoverkorea.client.hotplace.HotPlaceComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotPlaceCommentRepository extends JpaRepository<HotPlaceComment, Long> {
}
