package com.ssafy.discoverkorea.client.hotplace.service.dto;

import com.ssafy.discoverkorea.common.entity.UploadFile;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class AddHotPlaceDto {

    private String content;
    private String placeName;
    private String roadAddress;
    private Double longitude;
    private Double latitude;
    private List<UploadFile> files;

    @Builder
    public AddHotPlaceDto(String content, String placeName, String roadAddress, Double longitude, Double latitude, List<UploadFile> files) {
        this.content = content;
        this.placeName = placeName;
        this.roadAddress = roadAddress;
        this.longitude = longitude;
        this.latitude = latitude;
        this.files = files;
    }
}
