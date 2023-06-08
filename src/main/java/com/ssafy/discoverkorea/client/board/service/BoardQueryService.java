package com.ssafy.discoverkorea.client.board.service;

import com.ssafy.discoverkorea.client.api.response.board.BoardDetailResponse;
import com.ssafy.discoverkorea.client.api.response.board.BoardResponse;
import com.ssafy.discoverkorea.client.board.repository.dto.SearchBoardCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BoardQueryService {

    Page<BoardResponse> searchByCondition(SearchBoardCondition condition, Pageable pageable);

    BoardDetailResponse getBoard(Long boardId);
}
