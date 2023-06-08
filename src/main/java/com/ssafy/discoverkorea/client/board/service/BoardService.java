package com.ssafy.discoverkorea.client.board.service;

import com.ssafy.discoverkorea.client.board.service.dto.AddBoardDto;
import com.ssafy.discoverkorea.client.board.service.dto.EditBoardDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BoardService {

    Long addBoard(String loginId, AddBoardDto dto);

    Long editBoard(Long boardId, EditBoardDto dto);

    Long removeBoard(Long boardId);

    Long increaseHitCount(Long boardId);

    Long addBoardLike(String loginId, Long boardId);
}
