package com.ssafy.discoverkorea.admin.api;

import com.ssafy.discoverkorea.admin.admin.service.AdminAccountService;
import com.ssafy.discoverkorea.admin.api.request.admin.LoginAdminRequest;
import com.ssafy.discoverkorea.jwt.TokenInfo;
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
public class AdminAccountApiController {

    private final AdminAccountService adminAccountService;

    @ApiOperation(value = "관리자 로그인")
    @PostMapping("/login")
    public TokenInfo login(@Valid @RequestBody LoginAdminRequest request) {
        log.debug("LoginAdminRequest={}", request);

        TokenInfo tokenInfo = adminAccountService.login(request.getLoginId(), request.getLoginPw());
        log.debug("TokenInfo={}", tokenInfo);
        return tokenInfo;
    }
}
