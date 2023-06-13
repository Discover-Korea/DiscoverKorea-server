package com.ssafy.discoverkorea.client.hotplace;

import com.ssafy.discoverkorea.client.member.Member;
import com.ssafy.discoverkorea.common.entity.Active;
import com.ssafy.discoverkorea.common.entity.TimeBaseEntity;
import com.ssafy.discoverkorea.common.entity.UploadFile;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static com.ssafy.discoverkorea.common.entity.Active.*;
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

    //== 생성자 ==//
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

    //== 연관관계 편의 메서드==//
    public static HotPlace createHotPlace(Long memberId, String content, Place place, List<UploadFile> files) {
        HotPlace hotPlace = HotPlace.builder()
                .content(content)
                .place(place)
                .hitCount(0)
                .likeCount(0)
                .scrapCount(0)
                .commentCount(0)
                .active(ACTIVE)
                .member(Member.builder().id(memberId).build())
                .build();

        List<HotPlaceImage> images = new ArrayList<>();
        for (UploadFile file : files) {
            HotPlaceImage image = HotPlaceImage.builder()
                    .file(file)
                    .hotPlace(hotPlace)
                    .build();
            images.add(image);
        }

        hotPlace.insertImages(images);
        return hotPlace;
    }

    private void insertImages(List<HotPlaceImage> images) {
        this.images = images;
    }

    //== 비즈니스 로직 ==//
    public void edit(String content, Place place, List<HotPlaceImage> images) {
        this.content = content;
        this.place = place;
        this.images = images;
    }

    public void remove() {
        this.active = DEACTIVE;
    }

    public void increaseHitCount() {
        this.hitCount += 1;
    }

    public void increaseLikeCount() {
        this.likeCount += 1;
    }

    public void decreaseLikeCount() {
        this.likeCount -= 1;
    }
}
