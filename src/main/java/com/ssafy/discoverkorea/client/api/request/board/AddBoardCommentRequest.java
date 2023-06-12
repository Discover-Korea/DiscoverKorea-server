package com.ssafy.discoverkorea.client.api.request.board;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddBoardCommentRequest {

    private Long parentId;
    @NotBlank
    private String content;
}
