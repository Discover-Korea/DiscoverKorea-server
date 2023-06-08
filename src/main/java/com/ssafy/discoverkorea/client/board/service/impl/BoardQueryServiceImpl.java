package com.ssafy.discoverkorea.client.board.service.impl;

import com.ssafy.discoverkorea.client.api.response.board.BoardResponse;
import com.ssafy.discoverkorea.client.board.repository.BoardQueryRepository;
import com.ssafy.discoverkorea.client.board.repository.dto.SearchBoardCondition;
import com.ssafy.discoverkorea.client.board.service.BoardQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardQueryServiceImpl implements BoardQueryService {

    private final BoardQueryRepository boardQueryRepository;

    @Override
    public Page<BoardResponse> searchByCondition(SearchBoardCondition condition, Pageable pageable) {
        return null;
    }
}
