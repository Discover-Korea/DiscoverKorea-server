package com.ssafy.discoverkorea.client.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

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
}