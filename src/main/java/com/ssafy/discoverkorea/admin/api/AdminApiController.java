package com.ssafy.discoverkorea.admin.api;

import com.ssafy.discoverkorea.admin.admin.service.AdminService;
import com.ssafy.discoverkorea.admin.admin.service.dto.RegisterAdminDto;
import com.ssafy.discoverkorea.admin.api.dto.RegisterAdminRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Api(tags = {"관리자 계정"})
public class AdminApiController {

    private final AdminService adminService;

    @ApiOperation(value = "관리자 등록")
    @PostMapping
    public void register(@Valid @RequestBody RegisterAdminRequest request) {
        log.debug("RegisterAdminRequest={}", request);

        RegisterAdminDto dto = RegisterAdminDto.builder()
                .loginId(request.getLoginId())
                .loginPw(request.getLoginPw())
                .name(request.getName())
                .tel(request.getTel())
                .email(request.getEmail())
                .build();
        Long adminId = adminService.register(dto);
        log.debug("register={}", adminId);
    }
}
