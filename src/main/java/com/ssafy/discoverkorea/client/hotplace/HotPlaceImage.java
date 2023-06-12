package com.ssafy.discoverkorea.client.hotplace;

import com.ssafy.discoverkorea.common.entity.TimeBaseEntity;
import com.ssafy.discoverkorea.common.entity.UploadFile;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class HotPlaceImage extends TimeBaseEntity {

    @Id @GeneratedValue
    @Column(name = "hot_place_id")
    private Long id;
    @Embedded
    private UploadFile file;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hot_place_id")
    private HotPlace hotPlace;

    @Builder
    public HotPlaceImage(Long id, UploadFile file, HotPlace hotPlace) {
        this.id = id;
        this.file = file;
        this.hotPlace = hotPlace;
    }
}
