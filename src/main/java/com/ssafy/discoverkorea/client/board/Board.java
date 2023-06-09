package com.ssafy.discoverkorea.client.board;

import com.ssafy.discoverkorea.client.member.Member;
import com.ssafy.discoverkorea.common.entity.Active;
import com.ssafy.discoverkorea.common.entity.TimeBaseEntity;
import com.ssafy.discoverkorea.common.exception.NumberException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.ssafy.discoverkorea.common.entity.Active.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Board extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;
    @Column(nullable = false, length = 50)
    private String title;
    @Lob
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private int hitCount;
    @Column(nullable = false)
    private int likeCount;
    @Column(nullable = false)
    private int scrapCount;
    @Column(nullable = false)
    private int commentCount;
    @Embedded
    @Column(nullable = false, length = 20)
    private Active active;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //== 생성자 ==//
    @Builder
    public Board(Long id, String title, String content, int hitCount, int likeCount, int scrapCount, int commentCount, Active active, Member member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hitCount = hitCount;
        this.likeCount = likeCount;
        this.scrapCount = scrapCount;
        this.commentCount = commentCount;
        this.active = active;
        this.member = member;
    }

    //== 비즈니스 로직 ==//
    public void editBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void removeBoard() {
        this.active = DEACTIVE;
    }

    public void increaseHitCount() {
        this.hitCount += 1;
    }

    public void increaseLikeCount() {
        this.likeCount += 1;
    }

    public void decreaseLikeCount() {
        int likeCount = this.likeCount - 1;
        if (likeCount < 0) {
            throw new NumberException();
        }
        this.likeCount = likeCount;
    }

    public void increaseScrapCount() {
        this.scrapCount += 1;
    }
}
