package com.ssafy.discoverkorea.client.member.service.impl;

import com.ssafy.discoverkorea.client.member.Member;
import com.ssafy.discoverkorea.client.member.repository.MemberRepository;
import com.ssafy.discoverkorea.client.member.service.MemberService;
import com.ssafy.discoverkorea.client.member.service.dto.EditLoginPwDto;
import com.ssafy.discoverkorea.client.member.service.dto.SignupMemberDto;
import com.ssafy.discoverkorea.common.exception.DuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.ssafy.discoverkorea.common.entity.Active.ACTIVE;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Long signup(SignupMemberDto dto) {
        duplicateLoginId(dto.getLoginId());
        duplicateTel(dto.getTel());
        duplicateEmail(dto.getEmail());
        duplicateNickname(dto.getNickname());

        Member member = createMember(dto);

        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }

    @Override
    public Long editLoginPw(String loginId, EditLoginPwDto dto) {
        Member findMember = memberRepository.findByLoginId(loginId)
                .orElseThrow(NoSuchElementException::new);

        findMember.editLoginPw(dto.getNowLoginPw(), dto.getNewLoginPw());
        return findMember.getId();
    }

    @Override
    public Long editTel(String loginId, String newTel) {
        return null;
    }

    private void duplicateLoginId(String loginId) {
        Optional<Long> existLoginId = memberRepository.existLoginId(loginId);
        if (existLoginId.isPresent()) {
            throw new DuplicateException();
        }
    }

    private void duplicateTel(String tel) {
        Optional<Long> existTel = memberRepository.existTel(tel);
        if (existTel.isPresent()) {
            throw new DuplicateException();
        }
    }

    private void duplicateEmail(String email) {
        Optional<Long> existEmail = memberRepository.existEmail(email);
        if (existEmail.isPresent()) {
            throw new DuplicateException();
        }
    }

    private void duplicateNickname(String nickname) {
        Optional<Long> existNickname = memberRepository.existNickname(nickname);
        if (existNickname.isPresent()) {
            throw new DuplicateException();
        }
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
}
