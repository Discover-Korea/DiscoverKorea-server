package com.ssafy.discoverkorea.client.api.request.hotplace;

import lombok.Data;

import java.util.List;

@Data
public class EditHotPlaceRequest {

    private String content;
    private String placeName;
    private String roadAddress;
    private Double longitude;
    private Double latitude;
    private List<Long> removeImages;
}
