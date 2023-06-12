package com.ssafy.discoverkorea.client.board.service.impl;

import com.ssafy.discoverkorea.client.board.Board;
import com.ssafy.discoverkorea.client.board.BoardComment;
import com.ssafy.discoverkorea.client.board.BoardLike;
import com.ssafy.discoverkorea.client.board.BoardScrap;
import com.ssafy.discoverkorea.client.board.repository.BoardCommentRepository;
import com.ssafy.discoverkorea.client.board.repository.BoardLikeRepository;
import com.ssafy.discoverkorea.client.board.repository.BoardRepository;
import com.ssafy.discoverkorea.client.board.repository.BoardScrapRepository;
import com.ssafy.discoverkorea.client.board.service.BoardService;
import com.ssafy.discoverkorea.client.board.service.dto.AddBoardDto;
import com.ssafy.discoverkorea.client.board.service.dto.EditBoardDto;
import com.ssafy.discoverkorea.client.member.Member;
import com.ssafy.discoverkorea.client.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import static com.ssafy.discoverkorea.common.entity.Active.ACTIVE;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;
    private final BoardScrapRepository boardScrapRepository;
    private final BoardCommentRepository boardCommentRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long addBoard(String loginId, AddBoardDto dto) {
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(NoSuchElementException::new);

        Board board = toBoard(dto, member);

        Board savedBoard = boardRepository.save(board);
        return savedBoard.getId();
    }

    @Override
    public Long editBoard(Long boardId, EditBoardDto dto) {
        Board findBoard = boardRepository.findById(boardId)
                .orElseThrow(NoSuchElementException::new);

        findBoard.editBoard(dto.getTitle(), dto.getContent());
        return findBoard.getId();
    }

    @Override
    public Long removeBoard(Long boardId) {
        Board findBoard = boardRepository.findById(boardId)
                .orElseThrow(NoSuchElementException::new);

        findBoard.removeBoard();
        return findBoard.getId();
    }

    @Override
    public Long increaseHitCount(Long boardId) {
        Board findBoard = boardRepository.findById(boardId)
                .orElseThrow(NoSuchElementException::new);

        findBoard.increaseHitCount();
        return findBoard.getId();
    }

    @Override
    public Long addBoardLike(String loginId, Long boardId) {
        Member findMember = memberRepository.findByLoginId(loginId)
                .orElseThrow(NoSuchElementException::new);

        BoardLike boardLike = BoardLike.builder()
                .member(findMember)
                .board(Board.builder().id(boardId).build())
                .build();

        BoardLike savedBoardLike = boardLikeRepository.save(boardLike);
        return savedBoardLike.getId();
    }

    @Override
    public Long increaseLikeCount(Long boardId) {
        Board findBoard = boardRepository.findById(boardId)
                .orElseThrow(NoSuchElementException::new);

        findBoard.increaseLikeCount();
        return findBoard.getId();
    }

    @Override
    public Long cancelBoardLike(String loginId, Long boardId) {
        Long boardLikeId = boardLikeRepository.findByLoginIdAndBoardId(loginId, boardId)
                .orElseThrow(NoSuchElementException::new);
        boardLikeRepository.deleteById(boardLikeId);
        return boardLikeId;
    }

    @Override
    public Long decreaseLikeCount(Long boardId) {
        Board findBoard = boardRepository.findById(boardId)
                .orElseThrow(NoSuchElementException::new);

        findBoard.decreaseLikeCount();
        return findBoard.getId();
    }

    @Override
    public Long addBoardScrap(String loginId, Long boardId) {
        Member findMember = memberRepository.findByLoginId(loginId)
                .orElseThrow(NoSuchElementException::new);

        BoardScrap boardScrap = BoardScrap.builder()
                .member(findMember)
                .board(Board.builder().id(boardId).build())
                .build();

        BoardScrap savedBoardScrap = boardScrapRepository.save(boardScrap);
        return savedBoardScrap.getId();
    }

    @Override
    public Long increaseScrapCount(Long boardId) {
        Board findBoard = boardRepository.findById(boardId)
                .orElseThrow(NoSuchElementException::new);

        findBoard.increaseScrapCount();
        return findBoard.getId();
    }

    @Override
    public Long cancelBoardScrap(String loginId, Long boardId) {
        Long boardScrapId = boardScrapRepository.findByLoginIdAndBoardId(loginId, boardId)
                .orElseThrow(NoSuchElementException::new);

        boardScrapRepository.deleteById(boardScrapId);
        return boardScrapId;
    }

    @Override
    public Long decreaseScrapCount(Long boardId) {
        Board findBoard = boardRepository.findById(boardId)
                .orElseThrow(NoSuchElementException::new);

        findBoard.decreaseScrapCount();
        return findBoard.getId();
    }

    @Override
    public Long addBoardComment(String loginId, Long parentId, String content) {
        Member findMember = memberRepository.findByLoginId(loginId)
                .orElseThrow(NoSuchElementException::new);

        BoardComment boardComment = BoardComment.builder()
                .content(content)
                .member(findMember)
                .parent(BoardComment.builder()
                        .id(parentId)
                        .build())
                .active(ACTIVE)
                .build();

        BoardComment savedBoardComment = boardCommentRepository.save(boardComment);
        return savedBoardComment.getId();
    }

    @Override
    public Long removeBoardComment(Long boardCommentId) {
        return null;
    }

    private Board toBoard(AddBoardDto dto, Member member) {
        return Board.builder()
                .member(member)
                .title(dto.getTitle())
                .content(dto.getContent())
                .hitCount(0)
                .likeCount(0)
                .scrapCount(0)
                .commentCount(0)
                .active(ACTIVE)
                .build();
    }
}
