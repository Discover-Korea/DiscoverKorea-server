package com.ssafy.discoverkorea.client.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.ssafy.discoverkorea.common.entity.Active.ACTIVE;
import static com.ssafy.discoverkorea.common.entity.Active.DEACTIVE;
import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    @DisplayName("게시물 수정")
    void editBoard() {
        //given
        Board board = Board.builder()
                .title("board title")
                .content("board content")
                .build();
        String editTitle = "edit board title";
        String editContent = "edit board content";
            
        //when
        board.editBoard(editTitle, editContent);
            
        //then
        assertThat(board.getTitle()).isEqualTo(editTitle);
        assertThat(board.getContent()).isEqualTo(editContent);
    }

    @Test
    @DisplayName("게시물 삭제")
    void removeBoard() {
        //given
        Board board = Board.builder()
                .active(ACTIVE)
                .build();

        //when
        board.removeBoard();

        //then
        assertThat(board.getActive()).isEqualTo(DEACTIVE);
    }
}