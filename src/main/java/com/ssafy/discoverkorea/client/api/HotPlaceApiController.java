package com.ssafy.discoverkorea.client.api;

import com.ssafy.discoverkorea.client.api.request.hotplace.AddHotPlaceRequest;
import com.ssafy.discoverkorea.client.api.request.hotplace.EditHotPlaceRequest;
import com.ssafy.discoverkorea.client.hotplace.service.HotPlaceService;
import com.ssafy.discoverkorea.client.hotplace.service.dto.AddHotPlaceDto;
import com.ssafy.discoverkorea.client.hotplace.service.dto.EditHotPlaceDto;
import com.ssafy.discoverkorea.common.FileStore;
import com.ssafy.discoverkorea.common.entity.UploadFile;
import com.ssafy.discoverkorea.jwt.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @ApiOperation(value = "핫플레이스 수정")
    @PutMapping("/{hotPlaceId}")
    public void editHotPlace(@PathVariable Long hotPlaceId, @Valid @RequestBody EditHotPlaceRequest request) {
        log.debug("EditHotPlaceRequest={}", request);

        EditHotPlaceDto dto = EditHotPlaceDto.builder()
                .content(request.getContent())
                .placeName(request.getPlaceName())
                .roadAddress(request.getRoadAddress())
                .longitude(request.getLongitude())
                .latitude(request.getLatitude())
                .removeImages(request.getRemoveImages())
                .build();

        Long editedHotPlaceId = hotPlaceService.editHotPlace(hotPlaceId, dto);
        log.debug("editHotPlace={}", editedHotPlaceId);
    }

    @ApiOperation(value = "핫플레이스 삭제")
    @DeleteMapping("/{hotPlaceId}")
    public void removeHotPlace(@PathVariable Long hotPlaceId) {
        Long removedHotPlaceId = hotPlaceService.removeHotPlace(hotPlaceId);
        log.debug("removedHotPlace={}", removedHotPlaceId);
    }

    @ApiOperation(value = "핫플레이스 좋아요 등록")
    @PostMapping("/{hotPlaceId}/like")
    public void addHotPlaceLike(@PathVariable Long hotPlaceId) {
        log.debug("hotPlaceId={}", hotPlaceId);
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        Long hotPlaceLikeId = hotPlaceService.addLike(loginId, hotPlaceId);
        log.debug("addHotPlaceLike={}", hotPlaceLikeId);
    }

    @ApiOperation(value = "핫플레이스 좋아요 취소")
    @DeleteMapping("/{hotPlaceId}/like")
    public void cancelHotPlaceLike(@PathVariable Long hotPlaceId) {
        log.debug("hotPlaceId={}", hotPlaceId);
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        Long hotPlaceLikeId = hotPlaceService.cancelLike(loginId, hotPlaceId);
        log.debug("cancelHotPlaceLike={}", hotPlaceLikeId);
    }

    @ApiOperation(value = "핫플레이스 스크랩 등록")
    @PostMapping("/{hotPlaceId}/scrap")
    public void addHotPlaceScrap(@PathVariable Long hotPlaceId) {
        log.debug("hotPlaceId={}", hotPlaceId);
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        Long hotPlaceLikeId = hotPlaceService.addScrap(loginId, hotPlaceId);
        log.debug("addHotPlaceScrap={}", hotPlaceLikeId);
    }

    @ApiOperation(value = "핫플레이스 스크랩 취소")
    @DeleteMapping("/{hotPlaceId}/scrap")
    public void cancelPlaceScrap(@PathVariable Long hotPlaceId) {
        log.debug("hotPlaceId={}", hotPlaceId);
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        Long hotPlaceLikeId = hotPlaceService.cancelScrap(loginId, hotPlaceId);
        log.debug("cancelPlaceScrap={}", hotPlaceLikeId);
    }
}
