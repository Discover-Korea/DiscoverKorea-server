package com.ssafy.discoverkorea.client.api;

import com.ssafy.discoverkorea.client.api.request.board.AddBoardRequest;
import com.ssafy.discoverkorea.client.board.service.BoardService;
import com.ssafy.discoverkorea.client.board.service.dto.AddBoardDto;
import com.ssafy.discoverkorea.jwt.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
@Api(tags = {"게시물"})
public class BoardApiController {

    private final BoardService boardService;

    @ApiOperation(value = "게시글 등록")
    @PostMapping("/add")
    public void addBoard(@Valid @RequestBody AddBoardRequest request) {
        log.debug("AddBoardRequest={}", request);
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        AddBoardDto dto = AddBoardDto.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        Long boardId = boardService.addBoard(loginId, dto);
        log.debug("addBoard={}", boardId);
    }
}
