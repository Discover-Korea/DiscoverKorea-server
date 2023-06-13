package com.ssafy.discoverkorea.client.hotplace.service;

import com.ssafy.discoverkorea.client.hotplace.service.dto.AddHotPlaceDto;
import com.ssafy.discoverkorea.client.hotplace.service.dto.EditHotPlaceDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface HotPlaceService {

    Long addHotPlace(String loginId, AddHotPlaceDto dto);

    Long editHotPlace(Long hotPlaceId, EditHotPlaceDto dto);

    Long removeHotPlace(Long hotPlaceId);
}
