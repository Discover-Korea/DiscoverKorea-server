package com.ssafy.discoverkorea.client.hotplace.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class AddHotPlaceCommentDto {

    private Long parentId;
    private String content;

    @Builder
    public AddHotPlaceCommentDto(Long parentId, String content) {
        this.parentId = parentId;
        this.content = content;
    }
}
