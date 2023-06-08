package com.ssafy.discoverkorea.client.api.response.board;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class BoardResponse {

    private String title;
    private String content;
    private int hitCount;
    private int likeCount;
    private int scrapCount;
    private int commentCount;
    private String createdDate;

    private String nickname;

    public BoardResponse(String title, String content, int hitCount, int likeCount, int scrapCount, int commentCount, LocalDateTime createdDate, String nickname) {
        this.title = title;
        this.content = content;
        this.hitCount = hitCount;
        this.likeCount = likeCount;
        this.scrapCount = scrapCount;
        this.commentCount = commentCount;
        this.createdDate = createdDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        this.nickname = nickname;
    }
}
