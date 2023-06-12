package com.ssafy.discoverkorea.client.hotplace.service;

import com.ssafy.discoverkorea.client.hotplace.HotPlace;
import com.ssafy.discoverkorea.client.hotplace.repository.HotPlaceRepository;
import com.ssafy.discoverkorea.client.hotplace.service.dto.AddHotPlaceDto;
import com.ssafy.discoverkorea.client.member.Member;
import com.ssafy.discoverkorea.client.member.repository.MemberRepository;
import com.ssafy.discoverkorea.common.entity.UploadFile;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

import static com.ssafy.discoverkorea.client.member.Gender.MALE;
import static com.ssafy.discoverkorea.common.entity.Active.ACTIVE;
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
}