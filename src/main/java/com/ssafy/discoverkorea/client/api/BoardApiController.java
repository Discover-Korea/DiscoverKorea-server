package com.ssafy.discoverkorea.client.api;

import com.ssafy.discoverkorea.client.api.request.board.AddBoardRequest;
import com.ssafy.discoverkorea.client.api.request.board.EditBoardRequest;
import com.ssafy.discoverkorea.client.api.response.board.BoardDetailResponse;
import com.ssafy.discoverkorea.client.api.response.board.BoardResponse;
import com.ssafy.discoverkorea.client.board.repository.dto.SearchBoardCondition;
import com.ssafy.discoverkorea.client.board.service.BoardQueryService;
import com.ssafy.discoverkorea.client.board.service.BoardService;
import com.ssafy.discoverkorea.client.board.service.dto.AddBoardDto;
import com.ssafy.discoverkorea.client.board.service.dto.EditBoardDto;
import com.ssafy.discoverkorea.jwt.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
@Api(tags = {"게시물"})
public class BoardApiController {

    private final BoardService boardService;
    private final BoardQueryService boardQueryService;

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

    @ApiOperation(value = "게시글 삭제")
    @DeleteMapping("/{boardId}")
    public void removeBoard(@PathVariable Long boardId) {
        Long removeBoardId = boardService.removeBoard(boardId);
        log.debug("removeBoard={}", removeBoardId);
    }

    @ApiOperation(value = "게시글 목록 조회")
    @GetMapping
    public Page<BoardResponse> searchByCondition(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        log.debug("keyword={}, page={}", keyword, page);
        SearchBoardCondition condition = SearchBoardCondition.builder()
                .keyword(keyword)
                .build();
        PageRequest pageRequest = PageRequest.of(page - 1, 20);
        Page<BoardResponse> responses = boardQueryService.searchByCondition(condition, pageRequest);
        log.debug("BoardResponse size={}", responses.getContent().size());
        return responses;
    }

    @ApiOperation(value = "게시글 상세 조회")
    @GetMapping("/{boardId}")
    public BoardDetailResponse getBoard(@PathVariable Long boardId) {
        log.debug("boardId={}", boardId);
        boardService.increaseHitCount(boardId);
        BoardDetailResponse response = boardQueryService.getBoard(boardId);
        log.debug("BoardDetailResponse={}", response);
        return response;
    }

    @ApiOperation(value = "좋아요 등록")
    @PostMapping("/{boardId}/like")
    public void addBoardLike(@PathVariable Long boardId) {
        log.debug("boardId={}", boardId);
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        Long boardLikeId = boardService.addBoardLike(loginId, boardId);
        boardService.increaseLikeCount(boardId);
        log.debug("addBoardLike={}", boardLikeId);
    }

    @ApiOperation(value = "좋아요 취소")
    @DeleteMapping("/{boardId}/like")
    public void cancelBoardLike(@PathVariable Long boardId) {
        log.debug("boardId={}", boardId);
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        Long boardLikeId = boardService.cancelBoardLike(loginId, boardId);
        boardService.decreaseLikeCount(boardId);
        log.debug("addBoardLike={}", boardLikeId);
    }
}
