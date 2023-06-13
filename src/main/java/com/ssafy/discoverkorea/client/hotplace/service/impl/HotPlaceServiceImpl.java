package com.ssafy.discoverkorea.client.hotplace.service.impl;

import com.ssafy.discoverkorea.client.hotplace.HotPlace;
import com.ssafy.discoverkorea.client.hotplace.Place;
import com.ssafy.discoverkorea.client.hotplace.repository.HotPlaceRepository;
import com.ssafy.discoverkorea.client.hotplace.service.HotPlaceService;
import com.ssafy.discoverkorea.client.hotplace.service.dto.AddHotPlaceDto;
import com.ssafy.discoverkorea.client.hotplace.service.dto.EditHotPlaceDto;
import com.ssafy.discoverkorea.client.member.Member;
import com.ssafy.discoverkorea.client.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class HotPlaceServiceImpl implements HotPlaceService {

    private final HotPlaceRepository hotPlaceRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long addHotPlace(String loginId, AddHotPlaceDto dto) {
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(NoSuchElementException::new);

        Place place = Place.builder()
                .placeName(dto.getPlaceName())
                .roadAddress(dto.getRoadAddress())
                .longitude(dto.getLongitude())
                .latitude(dto.getLatitude())
                .build();
        HotPlace hotPlace = HotPlace.createHotPlace(member.getId(), dto.getContent(), place, dto.getFiles());

        HotPlace savedHotPlace = hotPlaceRepository.save(hotPlace);
        return savedHotPlace.getId();
    }

    @Override
    public Long editHotPlace(Long hotPlaceId, EditHotPlaceDto dto) {
        HotPlace hotPlace = hotPlaceRepository.findById(hotPlaceId)
                .orElseThrow(NoSuchElementException::new);

        return null;
    }
}
