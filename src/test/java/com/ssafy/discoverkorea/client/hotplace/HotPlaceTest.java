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

    @Test
    @DisplayName("좋아요수 증가")
    void increaseLikeCount() {
        //given
        HotPlace hotPlace = HotPlace.builder()
                .likeCount(0)
                .build();

        //when
        hotPlace.increaseLikeCount();

        //then
        assertThat(hotPlace.getLikeCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("좋아요수 감소")
    void decreaseLikeCount() {
        //given
        HotPlace hotPlace = HotPlace.builder()
                .likeCount(1)
                .build();

        //when
        hotPlace.decreaseLikeCount();

        //then
        assertThat(hotPlace.getLikeCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("스크랩수 증가")
    void increaseScrapCount() {
        //given
        HotPlace hotPlace = HotPlace.builder()
                .scrapCount(0)
                .build();

        //when
        hotPlace.increaseScrapCount();

        //then
        assertThat(hotPlace.getScrapCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("스크랩수 감소")
    void decreaseScrapCount() {
        //given
        HotPlace hotPlace = HotPlace.builder()
                .scrapCount(1)
                .build();

        //when
        hotPlace.decreaseScrapCount();

        //then
        assertThat(hotPlace.getScrapCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("댓글수 증가")
    void increaseCommentCount() {
        //given
        HotPlace hotPlace = HotPlace.builder()
                .commentCount(0)
                .build();

        //when
        hotPlace.increaseCommentCount();

        //then
        assertThat(hotPlace.getCommentCount()).isEqualTo(1);
    }
}