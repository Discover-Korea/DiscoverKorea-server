package com.ssafy.discoverkorea.client.api;

import com.ssafy.discoverkorea.client.api.request.member.*;
import com.ssafy.discoverkorea.client.member.service.MemberQueryService;
import com.ssafy.discoverkorea.client.member.service.MemberService;
import com.ssafy.discoverkorea.client.member.service.dto.EditEmailDto;
import com.ssafy.discoverkorea.client.member.service.dto.EditLoginPwDto;
import com.ssafy.discoverkorea.common.FileStore;
import com.ssafy.discoverkorea.common.entity.UploadFile;
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
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/my")
@Api(tags = {"마이페이지"})
public class MemberApiController {

    private final MemberService memberService;
    private final MemberQueryService memberQueryService;
    private final FileStore fileStore;

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

        memberQueryService.existTel(request.getNewTel());

        Long memberId = memberService.editTel(loginId, request.getNewTel());
        log.debug("editTel member={}", memberId);
    }

    @ApiOperation(value = "이메일 변경")
    @PutMapping("/email")
    public void editEmail(@Valid @RequestBody EditEmailRequest request) {
        log.debug("EditEmailRequest={}", request);
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        memberQueryService.existEmail(request.getNewEmail());
        EditEmailDto dto = EditEmailDto.builder()
                .newEmail(request.getNewEmail())
                .build();
        Long memberId = memberService.editEmail(loginId, dto);
        log.debug("editEmail member={}", memberId);
    }

    @ApiOperation(value = "닉네임 변경")
    @PutMapping("/nickname")
    public void editNickname(@Valid @RequestBody EditNicknameRequest request) {
        log.debug("EditNicknameRequest={}", request);
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        memberQueryService.existNickname(request.getNewNickname());
        Long memberId = memberService.editNickname(loginId, request.getNewNickname());
        log.debug("editNickname member={}", memberId);
    }

    @ApiOperation(value = "프로필 이미지 변경")
    @PutMapping("/profile")
    public void editProfile(EditProfileRequest request) throws IOException {
        log.debug("EditProfileRequest={}", request);
        UploadFile uploadFile = fileStore.storeFile(request.getFile());
        log.debug("UploadFile={}", uploadFile);
        String loginId = SecurityUtil.getCurrentLoginId();
        log.debug("loginId={}", loginId);

        Long memberId = memberService.editProfile(loginId, uploadFile);
        log.debug("editNickname member={}", memberId);
    }
}
