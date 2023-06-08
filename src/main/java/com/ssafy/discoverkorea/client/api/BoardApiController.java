package com.ssafy.discoverkorea.client.api;

import com.ssafy.discoverkorea.client.api.request.board.AddBoardRequest;
import com.ssafy.discoverkorea.client.api.request.board.EditBoardRequest;
import com.ssafy.discoverkorea.client.board.service.BoardService;
import com.ssafy.discoverkorea.client.board.service.dto.AddBoardDto;
import com.ssafy.discoverkorea.client.board.service.dto.EditBoardDto;
import com.ssafy.discoverkorea.jwt.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "게시글 수정")
    @PutMapping("/{boardId}")
    public void editBoard(@PathVariable Long boardId, @Valid @RequestBody EditBoardRequest request) {
        log.debug("EditBoardRequest={}", request);

        EditBoardDto dto = EditBoardDto.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        Long editBoardId = boardService.editBoard(boardId, dto);
        log.debug("editBoard={}", editBoardId);
    }
}
