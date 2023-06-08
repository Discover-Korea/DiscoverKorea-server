package com.ssafy.discoverkorea.client.board.service.impl;

import com.ssafy.discoverkorea.client.board.repository.BoardRepository;
import com.ssafy.discoverkorea.client.board.service.BoardService;
import com.ssafy.discoverkorea.client.board.service.dto.AddBoardDto;
import com.ssafy.discoverkorea.client.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long addBoard(String loginId, AddBoardDto dto) {
        return null;
    }
}
