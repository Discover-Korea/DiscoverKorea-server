package com.ssafy.discoverkorea.client.hotplace.service;

import com.ssafy.discoverkorea.client.hotplace.service.dto.AddHotPlaceCommentDto;
import com.ssafy.discoverkorea.client.hotplace.service.dto.AddHotPlaceDto;
import com.ssafy.discoverkorea.client.hotplace.service.dto.EditHotPlaceDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface HotPlaceService {

    Long addHotPlace(String loginId, AddHotPlaceDto dto);

    Long editHotPlace(Long hotPlaceId, EditHotPlaceDto dto);

    Long removeHotPlace(Long hotPlaceId);

    Long increaseHitCount(Long hotPlaceId);

    Long addLike(String loginId, Long hotPlaceId);

    Long cancelLike(String loginId, Long hotPlaceId);

    Long addScrap(String loginId, Long hotPlaceId);

    Long cancelScrap(String loginId, Long hotPlaceId);

    Long addComment(String loginId, Long hotPlaceId, AddHotPlaceCommentDto dto);

    Long removeComment(Long hotPlaceCommentId);
}
