package com.ssafy.discoverkorea.client.hotplace;

import com.ssafy.discoverkorea.client.member.Member;
import com.ssafy.discoverkorea.common.entity.Active;
import com.ssafy.discoverkorea.common.entity.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class HotPlace extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "hot_place_id")
    private Long id;
    @Lob
    @Column(nullable = false)
    private String content;
    @Embedded
    private Place place;
    private int hitCount;
    private int likeCount;
    private int scrapCount;
    private int commentCount;
    @Enumerated(STRING)
    @Column(nullable = false, length = 20)
    private Active active;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "hotPlace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotPlaceImage> images = new ArrayList<>();

    @Builder
    public HotPlace(Long id, String content, Place place, int hitCount, int likeCount, int scrapCount, int commentCount, Active active, Member member, List<HotPlaceImage> images) {
        this.id = id;
        this.content = content;
        this.place = place;
        this.hitCount = hitCount;
        this.likeCount = likeCount;
        this.scrapCount = scrapCount;
        this.commentCount = commentCount;
        this.active = active;
        this.member = member;
        this.images = images;
    }
}
