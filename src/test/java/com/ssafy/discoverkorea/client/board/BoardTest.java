package com.ssafy.discoverkorea.client.board;

import com.ssafy.discoverkorea.common.exception.NumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.ssafy.discoverkorea.common.entity.Active.ACTIVE;
import static com.ssafy.discoverkorea.common.entity.Active.DEACTIVE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    @DisplayName("게시물 조회수 증가")
    void increaseHitCount() {
        //given
        Board board = Board.builder()
                .hitCount(0)
                .build();

        //when
        board.increaseHitCount();

        //then
        assertThat(board.getHitCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("게시물 좋아요수 증가")
    void increaseLikeCount() {
        //given
        Board board = Board.builder()
                .likeCount(0)
                .build();

        //when
        board.increaseLikeCount();

        //then
        assertThat(board.getLikeCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("게시물 좋아요수 감소#음수")
    void likeCountNegativeNumber() {
        //given
        Board board = Board.builder()
                .likeCount(0)
                .build();
            
        //when
            
        //then
        assertThatThrownBy(() -> board.decreaseLikeCount())
                .isInstanceOf(NumberException.class);
        
    }
    
    @Test
    @DisplayName("게시물 좋아요수 감소")
    void decreaseLikeCount() {
        //given
        Board board = Board.builder()
                .likeCount(1)
                .build();

        //when
        board.decreaseLikeCount();

        //then
        assertThat(board.getLikeCount()).isEqualTo(0);
    }
}