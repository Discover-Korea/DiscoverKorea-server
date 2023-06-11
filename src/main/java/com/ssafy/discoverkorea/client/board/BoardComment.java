package com.ssafy.discoverkorea.client.board;

import com.ssafy.discoverkorea.client.member.Member;
import com.ssafy.discoverkorea.common.entity.Active;
import com.ssafy.discoverkorea.common.entity.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class BoardComment extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "board_comment_id")
    private Long id;
    @Lob
    @Column(nullable = false)
    private String content;
    @Enumerated(STRING)
    @Column(nullable = false, length = 20)
    @ColumnDefault(value = "ACTIVE")
    private Active active;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private BoardComment parent;

    //== 생성자 ==//
    @Builder
    public BoardComment(Long id, String content, Active active, Member member, BoardComment parent) {
        this.id = id;
        this.content = content;
        this.active = active;
        this.member = member;
        this.parent = parent;
    }
}
