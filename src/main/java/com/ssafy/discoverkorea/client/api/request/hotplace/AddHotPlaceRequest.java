package com.ssafy.discoverkorea.client.api.request.hotplace;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class AddHotPlaceRequest {

    private String content;
    private String placeName;
    private String roadAddress;
    private Double longitude;
    private Double latitude;
    private List<MultipartFile> files;
}
