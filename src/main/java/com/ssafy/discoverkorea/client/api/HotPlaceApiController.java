package com.ssafy.discoverkorea.client.api;

import com.ssafy.discoverkorea.client.api.request.hotplace.AddHotPlaceRequest;
import com.ssafy.discoverkorea.client.hotplace.service.HotPlaceService;
import com.ssafy.discoverkorea.client.hotplace.service.dto.AddHotPlaceDto;
import com.ssafy.discoverkorea.common.FileStore;
import com.ssafy.discoverkorea.common.entity.UploadFile;
import com.ssafy.discoverkorea.jwt.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hotPlaces")
@Api(tags = {"핫플레이스"})
public class HotPlaceApiController {

    private final HotPlaceService hotPlaceService;
    private final FileStore fileStore;

    @ApiOperation(value = "핫플레이스 등록")
    @PostMapping
    public void addHotPlace(AddHotPlaceRequest request) throws IOException {
        log.debug("AddHotPlaceRequest={}", request);
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        List<UploadFile> uploadFiles = fileStore.storeFiles(request.getFiles());

        AddHotPlaceDto dto = AddHotPlaceDto.builder()
                .content(request.getContent())
                .placeName(request.getPlaceName())
                .roadAddress(request.getRoadAddress())
                .longitude(request.getLongitude())
                .latitude(request.getLatitude())
                .files(uploadFiles)
                .build();

        Long hotPlaceId = hotPlaceService.addHotPlace(loginId, dto);
        log.debug("addHotPlace={}", hotPlaceId);
    }
}
