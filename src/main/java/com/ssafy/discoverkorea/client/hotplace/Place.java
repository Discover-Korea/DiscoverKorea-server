package com.ssafy.discoverkorea.client.hotplace;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Embeddable
@NoArgsConstructor(access = PROTECTED)
public class Place {

    @Column(length = 100)
    private String placeName;
    @Column(length = 100)
    private String roadAddress;
    @Column(precision = 20, scale = 17)
    private Double longitude;
    @Column(precision = 20, scale = 17)
    private Double latitude;

    @Builder
    public Place(String placeName, String roadAddress, Double longitude, Double latitude) {
        this.placeName = placeName;
        this.roadAddress = roadAddress;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
