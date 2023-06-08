package com.ssafy.discoverkorea.client.board.service;

import com.ssafy.discoverkorea.client.board.service.dto.AddBoardDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BoardService {

    Long addBoard(String loginId, AddBoardDto dto);
}
