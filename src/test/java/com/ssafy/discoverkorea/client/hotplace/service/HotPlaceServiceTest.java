package com.ssafy.discoverkorea.client.hotplace.service;

import com.ssafy.discoverkorea.client.hotplace.HotPlace;
import com.ssafy.discoverkorea.client.hotplace.HotPlaceLike;
import com.ssafy.discoverkorea.client.hotplace.Place;
import com.ssafy.discoverkorea.client.hotplace.repository.HotPlaceLikeRepository;
import com.ssafy.discoverkorea.client.hotplace.repository.HotPlaceRepository;
import com.ssafy.discoverkorea.client.hotplace.service.dto.AddHotPlaceDto;
import com.ssafy.discoverkorea.client.hotplace.service.dto.EditHotPlaceDto;
import com.ssafy.discoverkorea.client.member.Member;
import com.ssafy.discoverkorea.client.member.repository.MemberRepository;
import com.ssafy.discoverkorea.common.entity.UploadFile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static com.ssafy.discoverkorea.client.member.Gender.MALE;
import static com.ssafy.discoverkorea.common.entity.Active.ACTIVE;
import static com.ssafy.discoverkorea.common.entity.Active.DEACTIVE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class HotPlaceServiceTest {

    @Autowired
    private HotPlaceService hotPlaceService;
    @Autowired
    private HotPlaceRepository hotPlaceRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private HotPlaceLikeRepository hotPlaceLikeRepository;

    @Test
    @DisplayName("핫플레이스 등록")
    void addHotPlace() {
        //given
        Member member = insertMember();
        UploadFile file = UploadFile.builder()
                .uploadFileName("uploadFileName.jpg")
                .storeFileName("storeFileName.jpg")
                .build();
        AddHotPlaceDto dto = AddHotPlaceDto.builder()
                .content("add hotPlace content")
                .placeName("롯데월드")
                .roadAddress("서울 송파구 올림픽로 240")
                .longitude(127.09811980036908)
                .latitude(37.51113059993883)
                .files(Collections.singletonList(file))
                .build();

        //when
        Long hotPlaceId = hotPlaceService.addHotPlace(member.getLoginId(), dto);

        //then
        Optional<HotPlace> findHotPlace = hotPlaceRepository.findById(hotPlaceId);
        assertThat(findHotPlace).isPresent();
    }

    @Test
    @DisplayName("핫플레이스 수정")
    void editHotPlace() {
        //given
        HotPlace hotPlace = insertHotPlace();
        EditHotPlaceDto dto = EditHotPlaceDto.builder()
                .content("new hotPlace content")
                .placeName(hotPlace.getPlace().getPlaceName())
                .roadAddress(hotPlace.getPlace().getRoadAddress())
                .longitude(hotPlace.getPlace().getLongitude())
                .latitude(hotPlace.getPlace().getLatitude())
                .removeImages(new ArrayList<>())
                .build();

        //when
        Long hotPlaceId = hotPlaceService.editHotPlace(hotPlace.getId(), dto);

        //then
        Optional<HotPlace> findHotPlace = hotPlaceRepository.findById(hotPlaceId);
        assertThat(findHotPlace).isPresent();
        assertThat(findHotPlace.get().getContent()).isEqualTo(dto.getContent());
    }

    @Test
    @DisplayName("핫플레이스 삭제")
    void removeHotPlace() {
        //given
        HotPlace hotPlace = insertHotPlace();

        //when
        Long hotPlaceId = hotPlaceService.removeHotPlace(hotPlace.getId());

        //then
        Optional<HotPlace> findHotPlace = hotPlaceRepository.findById(hotPlaceId);
        assertThat(findHotPlace).isPresent();
        assertThat(findHotPlace.get().getActive()).isEqualTo(DEACTIVE);
    }

    @Test
    @DisplayName("핫플레이스 조회수 증가")
    void increaseHitCount() {
        //given
        HotPlace hotPlace = insertHotPlace();

        //when
        Long hotPlaceId = hotPlaceService.increaseHitCount(hotPlace.getId());

        //then
        Optional<HotPlace> findHotPlace = hotPlaceRepository.findById(hotPlaceId);
        assertThat(findHotPlace).isPresent();
        assertThat(findHotPlace.get().getHitCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("핫플레이스 좋아요 등록")
    void addLike() {
        //given
        Member member = insertMember();
        HotPlace hotPlace = insertHotPlace();

        //when
        Long hotPlaceLikeId = hotPlaceService.addLike(member.getLoginId(), hotPlace.getId());

        //then
        Optional<HotPlaceLike> findHotPlaceLike = hotPlaceLikeRepository.findById(hotPlaceLikeId);
        assertThat(findHotPlaceLike).isPresent();
    }

    @Test
    @DisplayName("핫플레이스 좋아요수 증가")
    void increaseLikeCount() {
        //given
        Member member = insertMember();
        HotPlace hotPlace = insertHotPlace();

        //when
        Long hotPlaceLikeId = hotPlaceService.addLike(member.getLoginId(), hotPlace.getId());

        //then
        Optional<HotPlace> findHotPlace = hotPlaceRepository.findById(hotPlace.getId());
        assertThat(findHotPlace).isPresent();
        assertThat(findHotPlace.get().getLikeCount()).isEqualTo(1);
    }

    private Member insertMember() {
        Member member = Member.builder()
                .loginId("ssafy")
                .loginPw("ssafy1234!")
                .name("김싸피")
                .tel("010-1234-1234")
                .email("ssafy@ssafy.com")
                .birth("1998")
                .gender(MALE)
                .nickname("동팔이")
                .active(ACTIVE)
                .build();
        return memberRepository.save(member);
    }

    private HotPlace insertHotPlace() {
        UploadFile file = UploadFile.builder()
                .uploadFileName("uploadFileName.jpg")
                .storeFileName("storeFileName.jpg")
                .build();
        Place place = Place.builder()
                .placeName("롯데월드")
                .roadAddress("서울 송파구 올림픽로 240")
                .longitude(127.09811980036908)
                .latitude(37.51113059993883)
                .build();
        HotPlace hotPlace = HotPlace.createHotPlace(null, "hotPlace content", place, Collections.singletonList(file));
        return hotPlaceRepository.save(hotPlace);
    }
}