package com.ssafy.discoverkorea.admin.api;

import com.ssafy.discoverkorea.admin.admin.service.AdminService;
import com.ssafy.discoverkorea.admin.admin.service.dto.EditLoginPwDto;
import com.ssafy.discoverkorea.admin.admin.service.dto.RegisterAdminDto;
import com.ssafy.discoverkorea.admin.api.request.admin.EditLoginPwRequest;
import com.ssafy.discoverkorea.admin.api.request.admin.EditTelRequest;
import com.ssafy.discoverkorea.admin.api.request.admin.RegisterAdminRequest;
import com.ssafy.discoverkorea.jwt.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "비밀번호 변경")
    @PutMapping("/loginPw")
    public void editLoginPw(@Valid @RequestBody EditLoginPwRequest request) {
        log.debug("EditLoginPwRequest={}", request);
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        EditLoginPwDto dto = EditLoginPwDto.builder()
                .nowLoginPw(request.getNowLoginPw())
                .newLoginPw(request.getNewLoginPw())
                .build();
        Long adminId = adminService.editLoginPw(loginId, dto);
        log.debug("editLoginPw={}", adminId);
    }

    @ApiOperation(value = "연락처 변경")
    @PutMapping("/tel")
    public void editTel(@Valid @RequestBody EditTelRequest request) {
        log.debug("EditTelRequest={}", request);
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        Long adminId = adminService.editTel(loginId, request.getNewTel());
        log.debug("editTel={}", adminId);
    }
}