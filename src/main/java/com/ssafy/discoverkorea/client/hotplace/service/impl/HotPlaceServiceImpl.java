package com.ssafy.discoverkorea.client.hotplace.service.impl;

import com.ssafy.discoverkorea.client.hotplace.*;
import com.ssafy.discoverkorea.client.hotplace.repository.HotPlaceCommentRepository;
import com.ssafy.discoverkorea.client.hotplace.repository.HotPlaceLikeRepository;
import com.ssafy.discoverkorea.client.hotplace.repository.HotPlaceRepository;
import com.ssafy.discoverkorea.client.hotplace.repository.HotPlaceScrapRepository;
import com.ssafy.discoverkorea.client.hotplace.service.HotPlaceService;
import com.ssafy.discoverkorea.client.hotplace.service.dto.AddHotPlaceCommentDto;
import com.ssafy.discoverkorea.client.hotplace.service.dto.AddHotPlaceDto;
import com.ssafy.discoverkorea.client.hotplace.service.dto.EditHotPlaceDto;
import com.ssafy.discoverkorea.client.member.Member;
import com.ssafy.discoverkorea.client.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.ssafy.discoverkorea.common.entity.Active.ACTIVE;

@Service
@RequiredArgsConstructor
public class HotPlaceServiceImpl implements HotPlaceService {

    private final HotPlaceRepository hotPlaceRepository;
    private final HotPlaceLikeRepository hotPlaceLikeRepository;
    private final HotPlaceScrapRepository hotPlaceScrapRepository;
    private final HotPlaceCommentRepository hotPlaceCommentRepository;
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

        Place place = Place.builder()
                .placeName(dto.getPlaceName())
                .roadAddress(dto.getRoadAddress())
                .longitude(dto.getLongitude())
                .latitude(dto.getLatitude())
                .build();

        List<HotPlaceImage> images = new ArrayList<>();
        for (HotPlaceImage image : hotPlace.getImages()) {
            if (!dto.getRemoveImages().contains(image.getId())) {
                images.add(image);
            }
        }

        hotPlace.edit(dto.getContent(), place, images);
        return hotPlace.getId();
    }

    @Override
    public Long removeHotPlace(Long hotPlaceId) {
        HotPlace hotPlace = hotPlaceRepository.findById(hotPlaceId)
                .orElseThrow(NoSuchElementException::new);

        hotPlace.remove();
        return hotPlace.getId();
    }

    @Override
    public Long increaseHitCount(Long hotPlaceId) {
        HotPlace hotPlace = hotPlaceRepository.findById(hotPlaceId)
                .orElseThrow(NoSuchElementException::new);

        hotPlace.increaseHitCount();
        return hotPlace.getId();
    }

    @Override
    public Long addLike(String loginId, Long hotPlaceId) {
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(NoSuchElementException::new);

        HotPlace hotPlace = hotPlaceRepository.findById(hotPlaceId)
                .orElseThrow(NoSuchElementException::new);

        HotPlaceLike hotPlaceLike = HotPlaceLike.builder()
                .member(member)
                .hotPlace(hotPlace)
                .build();
        HotPlaceLike savedHotPlaceLike = hotPlaceLikeRepository.save(hotPlaceLike);

        hotPlace.increaseLikeCount();

        return savedHotPlaceLike.getId();
    }

    @Override
    public Long cancelLike(String loginId, Long hotPlaceId) {
        Long hotPlaceLikeId = hotPlaceLikeRepository.findByLoginIdAndHotPlaceId(loginId, hotPlaceId)
                .orElseThrow(NoSuchElementException::new);

        HotPlace hotPlace = hotPlaceRepository.findById(hotPlaceId)
                .orElseThrow(NoSuchElementException::new);
        hotPlace.decreaseLikeCount();

        hotPlaceLikeRepository.deleteById(hotPlaceLikeId);
        return hotPlaceLikeId;
    }

    @Override
    public Long addScrap(String loginId, Long hotPlaceId) {
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(NoSuchElementException::new);

        HotPlace hotPlace = hotPlaceRepository.findById(hotPlaceId)
                .orElseThrow(NoSuchElementException::new);

        HotPlaceScrap hotPlaceScrap = HotPlaceScrap.builder()
                .member(member)
                .hotPlace(hotPlace)
                .build();
        HotPlaceScrap savedHotPlaceScrap = hotPlaceScrapRepository.save(hotPlaceScrap);

        hotPlace.increaseScrapCount();

        return savedHotPlaceScrap.getId();
    }

    @Override
    public Long cancelScrap(String loginId, Long hotPlaceId) {
        Long hotPlaceScrapId = hotPlaceScrapRepository.findByLoginIdAndHotPlaceId(loginId, hotPlaceId)
                .orElseThrow(NoSuchElementException::new);

        HotPlace hotPlace = hotPlaceRepository.findById(hotPlaceId)
                .orElseThrow(NoSuchElementException::new);
        hotPlace.decreaseScrapCount();

        hotPlaceScrapRepository.deleteById(hotPlaceScrapId);
        return hotPlaceScrapId;
    }

    @Override
    public Long addComment(String loginId, Long hotPlaceId, AddHotPlaceCommentDto dto) {
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(NoSuchElementException::new);

        HotPlace hotPlace = hotPlaceRepository.findById(hotPlaceId)
                .orElseThrow(NoSuchElementException::new);

        HotPlaceComment hotPlaceComment = HotPlaceComment.builder()
                .content(dto.getContent())
                .active(ACTIVE)
                .member(member)
                .hotPlace(hotPlace)
                .parent(HotPlaceComment.builder().id(dto.getParentId()).build())
                .build();

        HotPlaceComment savedHotPlaceComment = hotPlaceCommentRepository.save(hotPlaceComment);

        hotPlace.increaseCommentCount();
        return savedHotPlaceComment.getId();
    }

    @Override
    public Long removeComment(Long hotPlaceCommentId) {
        HotPlaceComment hotPlaceComment = hotPlaceCommentRepository.findJoinHotPlaceById(hotPlaceCommentId)
                .orElseThrow(NoSuchElementException::new);
        hotPlaceComment.remove();

        hotPlaceComment.getHotPlace().decreaseCommentCount();
        return hotPlaceComment.getId();
    }
}
