package com.ssafy.discoverkorea.client.api;

import com.ssafy.discoverkorea.client.api.request.member.EditLoginPwRequest;
import com.ssafy.discoverkorea.client.api.request.member.EditTelRequest;
import com.ssafy.discoverkorea.client.member.service.MemberService;
import com.ssafy.discoverkorea.client.member.service.dto.EditLoginPwDto;
import com.ssafy.discoverkorea.jwt.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/my")
@Api(tags = {"마이페이지"})
public class MemberApiController {

    private final MemberService memberService;

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
        Long memberId = memberService.editLoginPw(loginId, dto);
        log.debug("editLoginPw member={}", memberId);
    }

    @ApiOperation(value = "연락처 변경")
    @PutMapping("/tel")
    public void editTel(@Valid @RequestBody EditTelRequest request) {
        log.debug("EditTelRequest={}", request);
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        Long memberId = memberService.editTel(loginId, request.getNewTel());
        log.debug("editTel memnber={}", memberId);
    }
}