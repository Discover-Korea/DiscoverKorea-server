package com.ssafy.discoverkorea.client.board.service;

import com.ssafy.discoverkorea.client.board.Board;
import com.ssafy.discoverkorea.client.board.BoardLike;
import com.ssafy.discoverkorea.client.board.BoardScrap;
import com.ssafy.discoverkorea.client.board.repository.BoardLikeRepository;
import com.ssafy.discoverkorea.client.board.repository.BoardRepository;
import com.ssafy.discoverkorea.client.board.repository.BoardScrapRepository;
import com.ssafy.discoverkorea.client.board.service.dto.AddBoardDto;
import com.ssafy.discoverkorea.client.board.service.dto.EditBoardDto;
import com.ssafy.discoverkorea.client.member.Member;
import com.ssafy.discoverkorea.client.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.ssafy.discoverkorea.client.member.Gender.MALE;
import static com.ssafy.discoverkorea.common.entity.Active.ACTIVE;
import static com.ssafy.discoverkorea.common.entity.Active.DEACTIVE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BoardLikeRepository boardLikeRepository;
    @Autowired
    private BoardScrapRepository boardScrapRepository;

    @Test
    @DisplayName("게시글 등록")
    void addBoard() {
        //given
        Member member = insertMember();
        AddBoardDto dto = AddBoardDto.builder()
                .title("board title")
                .content("board content")
                .build();

        //when
        Long boardId = boardService.addBoard(member.getLoginId(), dto);

        //then
        Optional<Board> findBoard = boardRepository.findById(boardId);
        assertThat(findBoard).isPresent();
    }

    @Test
    @DisplayName("게시글 수정")
    void editBoard() {
        //given
        Board board = insertBoard();
        EditBoardDto dto = EditBoardDto.builder()
                .title("edit board title")
                .content("edit board content")
                .build();

        //when
        Long boardId = boardService.editBoard(board.getId(), dto);

        //then
        Optional<Board> findBoard = boardRepository.findById(boardId);
        assertThat(findBoard).isPresent();
        assertThat(findBoard.get().getTitle()).isEqualTo(dto.getTitle());
        assertThat(findBoard.get().getContent()).isEqualTo(dto.getContent());
    }

    @Test
    @DisplayName("게시글 삭제")
    void removeBoard() {
        //given
        Board board = insertBoard();

        //when
        Long boardId = boardService.removeBoard(board.getId());

        //then
        Optional<Board> findBoard = boardRepository.findById(boardId);
        assertThat(findBoard).isPresent();
        assertThat(findBoard.get().getActive()).isEqualTo(DEACTIVE);
    }

    @Test
    @DisplayName("게시글 조회수 증가")
    void increaseHitCount() {
        //given
        Board board = insertBoard();

        //when
        Long boardId = boardService.increaseHitCount(board.getId());

        //then
        Optional<Board> findBoard = boardRepository.findById(boardId);
        assertThat(findBoard).isPresent();
        assertThat(findBoard.get().getHitCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("게시글 좋아요 등록")
    void addBoardLike() {
        //given
        Member member = insertMember();
        Board board = insertBoard();

        //when
        Long boardLikeId = boardService.addBoardLike(member.getLoginId(), board.getId());

        //then
        Optional<BoardLike> findBoardLike = boardLikeRepository.findById(boardLikeId);
        assertThat(findBoardLike).isPresent();
    }

    @Test
    @DisplayName("게시글 좋아요수 증가")
    void increaseLikeCount() {
        //given
        Board board = insertBoard();

        //when
        Long boardId = boardService.increaseLikeCount(board.getId());

        //then
        Optional<Board> findBoard = boardRepository.findById(boardId);
        assertThat(findBoard).isPresent();
        assertThat(findBoard.get().getLikeCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("게시글 좋아요 취소")
    void cancelBoardLike() {
        //given
        BoardLike boardLike = insertBoardLike();

        //when
        Long boardLikeId = boardService.cancelBoardLike(
                boardLike.getMember().getLoginId(),
                boardLike.getBoard().getId());

        //then
        Optional<BoardLike> findBoardLike = boardLikeRepository.findById(boardLikeId);
        assertThat(findBoardLike).isEmpty();
    }

    @Test
    @DisplayName("게시글 좋아요수 감소")
    void decreaseLikeCount() {
        //given
        Board board = insertBoard();
        board.increaseLikeCount();

        //when
        Long boardId = boardService.decreaseLikeCount(board.getId());

        //then
        Optional<Board> findBoard = boardRepository.findById(boardId);
        assertThat(findBoard).isPresent();
        assertThat(findBoard.get().getLikeCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("게시글 스크랩 등록")
    void addBoardScrap() {
        //given
        Member member = insertMember();
        Board board = insertBoard();

        //when
        Long boardScrapId = boardService.addBoardScrap(member.getLoginId(), board.getId());

        //then
        Optional<BoardScrap> findBoardScrap = boardScrapRepository.findById(boardScrapId);
        assertThat(findBoardScrap).isPresent();
    }

    @Test
    @DisplayName("게시글 스크랩수 증가")
    void increaseScrapCount() {
        //given
        Board board = insertBoard();

        //when
        Long boardId = boardService.increaseScrapCount(board.getId());

        //then
        Optional<Board> findBoard = boardRepository.findById(boardId);
        assertThat(findBoard).isPresent();
        assertThat(findBoard.get().getScrapCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("게시글 스크랩 취소")
    void cancelBoardScrap() {
        //given
        BoardScrap boardScrap = insertBoardScrap();

        //when
        Long boardScrapId = boardService.cancelBoardScrap(boardScrap.getMember().getLoginId(), boardScrap.getBoard().getId());

        //then
        Optional<BoardScrap> findBoardScrap = boardScrapRepository.findById(boardScrapId);
        assertThat(findBoardScrap).isEmpty();
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

    private Board insertBoard() {
        Board board = Board.builder()
                .title("board title")
                .content("board content")
                .active(ACTIVE)
                .build();
        return boardRepository.save(board);
    }

    private BoardLike insertBoardLike() {
        BoardLike boardLike = BoardLike.builder()
                .member(insertMember())
                .board(insertBoard())
                .build();
        return boardLikeRepository.save(boardLike);
    }

    private BoardScrap insertBoardScrap() {
        BoardScrap boardScrap = BoardScrap.builder()
                .member(insertMember())
                .board(insertBoard())
                .build();
        return boardScrapRepository.save(boardScrap);
    }
}