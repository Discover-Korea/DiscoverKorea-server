package com.ssafy.discoverkorea.client.board.repository.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class SearchBoardCondition {

    private String keyword;

    @Builder
    public SearchBoardCondition(String keyword) {
        this.keyword = keyword;
    }
}
