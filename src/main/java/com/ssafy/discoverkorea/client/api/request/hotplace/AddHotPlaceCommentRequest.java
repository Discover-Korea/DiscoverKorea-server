package com.ssafy.discoverkorea.client.api.request.hotplace;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddHotPlaceCommentRequest {

    private Long parentId;
    @NotBlank
    private String content;
}
