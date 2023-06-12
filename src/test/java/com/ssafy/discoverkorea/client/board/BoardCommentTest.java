package com.ssafy.discoverkorea.client.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.ssafy.discoverkorea.common.entity.Active.ACTIVE;
import static com.ssafy.discoverkorea.common.entity.Active.DEACTIVE;
import static org.assertj.core.api.Assertions.*;

class BoardCommentTest {

    @Test
    @DisplayName("게시글 댓글 삭제")
    void remove() {
        //given
        BoardComment boardComment = BoardComment.builder()
                .active(ACTIVE)
                .build();

        //when
        boardComment.remove();

        //then
        assertThat(boardComment.getActive()).isEqualTo(DEACTIVE);
    }
}