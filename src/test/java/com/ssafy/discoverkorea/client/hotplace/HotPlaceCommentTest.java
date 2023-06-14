package com.ssafy.discoverkorea.client.hotplace;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.ssafy.discoverkorea.common.entity.Active.ACTIVE;
import static com.ssafy.discoverkorea.common.entity.Active.DEACTIVE;
import static org.assertj.core.api.Assertions.*;

class HotPlaceCommentTest {

    @Test
    @DisplayName("핫플레이스 댓글 삭제")
    void remove() {
        //given
        HotPlaceComment hotPlaceComment = HotPlaceComment.builder()
                .active(ACTIVE)
                .build();

        //when
        hotPlaceComment.remove();

        //then
        assertThat(hotPlaceComment.getActive()).isEqualTo(DEACTIVE)
    }
}