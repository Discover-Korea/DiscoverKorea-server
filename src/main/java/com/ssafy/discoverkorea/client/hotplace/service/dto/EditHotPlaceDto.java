package com.ssafy.discoverkorea.client.hotplace.service.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class EditHotPlaceDto {

    private String content;
    private String placeName;
    private String roadAddress;
    private Double longitude;
    private Double latitude;
    private List<Long> removeImages;

    @Builder
    public EditHotPlaceDto(String content, String placeName, String roadAddress, Double longitude, Double latitude, List<Long> removeImages) {
        this.content = content;
        this.placeName = placeName;
        this.roadAddress = roadAddress;
        this.longitude = longitude;
        this.latitude = latitude;
        this.removeImages = removeImages;
    }
}
