package com.ssafy.discoverkorea.client.api;

import com.ssafy.discoverkorea.client.api.request.member.LoginMemberRequest;
import com.ssafy.discoverkorea.client.api.request.member.SignupMemberRequest;
import com.ssafy.discoverkorea.client.member.service.AccountService;
import com.ssafy.discoverkorea.client.member.service.MemberService;
import com.ssafy.discoverkorea.client.member.service.dto.SignupMemberDto;
import com.ssafy.discoverkorea.jwt.TokenInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = {"회원계정"})
public class AccountApiController {

    private final MemberService memberService;
    private final AccountService accountService;

    @ApiOperation(value = "회원가입")
    @PostMapping("/signup")
    public void signup(@Valid @RequestBody SignupMemberRequest request) {
        log.debug("SignupMemberRequest={}", request);
        SignupMemberDto dto = toSignupMemberDto(request);

        Long memberId = memberService.signup(dto);
        log.debug("signup member={}", memberId);
    }

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public TokenInfo login(@Valid @RequestBody LoginMemberRequest request) {
        log.debug("LoginMemberRequest={}", request);
        TokenInfo tokenInfo = accountService.login(request.getLoginId(), request.getLoginPw());

        log.debug("TokenInfo={}", tokenInfo);
        return tokenInfo;
    }

    private SignupMemberDto toSignupMemberDto(SignupMemberRequest request) {
        return SignupMemberDto.builder()
                .loginId(request.getLoginId())
                .loginPw(request.getLoginPw())
                .name(request.getName())
                .tel(request.getTel())
                .email(request.getEmail())
                .birth(request.getBirth())
                .gender(request.getGender())
                .nickname(request.getNickname())
                .build();
    }
}
