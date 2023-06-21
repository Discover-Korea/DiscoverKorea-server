package com.ssafy.discoverkorea.client.member.service;

import com.ssafy.discoverkorea.client.member.Member;
import com.ssafy.discoverkorea.client.member.repository.MemberRepository;
import com.ssafy.discoverkorea.client.member.service.dto.EditEmailDto;
import com.ssafy.discoverkorea.client.member.service.dto.EditLoginPwDto;
import com.ssafy.discoverkorea.client.member.service.dto.SignupMemberDto;
import com.ssafy.discoverkorea.common.entity.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.NoSuchElementException;

import static com.ssafy.discoverkorea.common.entity.Active.ACTIVE;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public Long signup(SignupMemberDto dto) {
        Member member = createMember(dto);
        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }

    public Long editLoginPw(String loginId, EditLoginPwDto dto) {
        Member member = getMember(loginId);
        member.editLoginPw(dto.getNowLoginPw(), dto.getNewLoginPw());
        return member.getId();
    }

    public Long editTel(String loginId, String newTel) {
        Member member = getMember(loginId);
        member.editTel(newTel);
        return member.getId();
    }

    public Long editEmail(String loginId, EditEmailDto dto) {
        Member member = getMember(loginId);
        member.editEmail(dto.getNewEmail());
        return member.getId();
    }

    public Long editNickname(String loginId, String newNickname) {
        Member member = getMember(loginId);
        member.editNickname(newNickname);
        return member.getId();
    }

    public Long editProfile(String loginId, UploadFile uploadFile) {
        Member member = getMember(loginId);
        member.editProfile(uploadFile);
        return member.getId();
    }

    public Long withdrawal(String loginId, String loginPw) {
        Member member = getMember(loginId);
        member.withdrawal(loginPw);
        return member.getId();
    }

    private Member createMember(SignupMemberDto dto) {
        return Member.builder()
                .loginId(dto.getLoginId())
                .loginPw(dto.getLoginPw())
                .name(dto.getName())
                .tel(dto.getTel())
                .email(dto.getEmail())
                .birth(dto.getBirth())
                .gender(dto.getGender())
                .nickname(dto.getNickname())
                .uploadFile(null)
                .active(ACTIVE)
                .roles(Collections.singletonList("MEMBER"))
                .build();
    }

    private Member getMember(String loginId) {
        return memberRepository.findByLoginId(loginId)
                .orElseThrow(NoSuchElementException::new);
    }
}
