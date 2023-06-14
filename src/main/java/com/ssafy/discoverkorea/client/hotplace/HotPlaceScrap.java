package com.ssafy.discoverkorea.client.hotplace;

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
public class HotPlaceScrap extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "hot_place_scrap_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hot_place_id")
    private HotPlace hotPlace;

    //== 생성자 ==//
    @Builder
    public HotPlaceScrap(Long id, Member member, HotPlace hotPlace) {
        this.id = id;
        this.member = member;
        this.hotPlace = hotPlace;
    }
}
