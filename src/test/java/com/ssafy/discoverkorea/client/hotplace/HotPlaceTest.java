package com.ssafy.discoverkorea.client.hotplace;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

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
}