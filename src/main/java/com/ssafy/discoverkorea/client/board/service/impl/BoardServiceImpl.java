package com.ssafy.discoverkorea.client.board.service.impl;

import com.ssafy.discoverkorea.client.board.Board;
import com.ssafy.discoverkorea.client.board.repository.BoardRepository;
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
