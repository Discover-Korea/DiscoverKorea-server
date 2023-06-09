package com.ssafy.discoverkorea.client.board;

import com.ssafy.discoverkorea.client.member.Member;
import com.ssafy.discoverkorea.common.entity.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class BoardScrap extends TimeBaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "board_scrap_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    //== 생성자 ==//
    @Builder
    public BoardScrap(Long id, Member member, Board board) {
        this.id = id;
        this.member = member;
        this.board = board;
    }
}
