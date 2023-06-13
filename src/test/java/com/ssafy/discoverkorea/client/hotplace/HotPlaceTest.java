package com.ssafy.discoverkorea.client.hotplace;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.ssafy.discoverkorea.common.entity.Active.ACTIVE;
import static com.ssafy.discoverkorea.common.entity.Active.DEACTIVE;
import static org.assertj.core.api.Assertions.assertThat;

class HotPlaceTest {

    @Test
    @DisplayName("핫플레이스 수정")
    void editHotPlace() {
        //given
        HotPlace hotPlace = HotPlace.builder()
                .content("hotPlace content")
                .build();
        String newContent = "new hotPlace content";

        //when
        hotPlace.edit(newContent, null, null);

        //then
        assertThat(hotPlace.getContent()).isEqualTo(newContent);
    }

    @Test
    @DisplayName("핫플레이스 삭제")
    void removeHotPlace() {
        //given
        HotPlace hotPlace = HotPlace.builder()
                .active(ACTIVE)
                .build();

        //when
        hotPlace.remove();

        //then
        assertThat(hotPlace.getActive()).isEqualTo(DEACTIVE);
    }

    @Test
    @DisplayName("조회수 증가")
    void increaseHitCount() {
        //given
        HotPlace hotPlace = HotPlace.builder()
                .hitCount(0)
                .build();

        //when
        hotPlace.increaseHitCount();

        //then
        assertThat(hotPlace.getHitCount()).isEqualTo(1);
    }
}