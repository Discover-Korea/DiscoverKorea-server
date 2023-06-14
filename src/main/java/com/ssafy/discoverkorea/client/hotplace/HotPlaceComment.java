package com.ssafy.discoverkorea.client.hotplace;

import com.ssafy.discoverkorea.client.member.Member;
import com.ssafy.discoverkorea.common.entity.Active;
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
public class HotPlaceComment extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "hot_place_comment_id")
    private Long id;
    @Lob
    @Column(nullable = false)
    private String content;
    @Embedded
    @Column(nullable = false, length = 20)
    private Active active;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hot_place_id")
    private HotPlace hotPlace;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private HotPlaceComment parent;

    @Builder
    public HotPlaceComment(Long id, String content, Active active, Member member, HotPlace hotPlace, HotPlaceComment parent) {
        this.id = id;
        this.content = content;
        this.active = active;
        this.member = member;
        this.hotPlace = hotPlace;
        this.parent = parent;
    }
}
