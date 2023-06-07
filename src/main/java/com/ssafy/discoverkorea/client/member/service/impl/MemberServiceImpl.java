package com.ssafy.discoverkorea.client.member.service.impl;

import com.ssafy.discoverkorea.client.member.Member;
import com.ssafy.discoverkorea.client.member.repository.MemberRepository;
import com.ssafy.discoverkorea.client.member.service.MemberService;
import com.ssafy.discoverkorea.client.member.service.dto.SignupMemberDto;
import com.ssafy.discoverkorea.common.exception.DuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

import static com.ssafy.discoverkorea.common.entity.Active.ACTIVE;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Long signup(SignupMemberDto dto) {
        Optional<Long> loginId = memberRepository.existLoginId(dto.getLoginId());
        if (loginId.isPresent()) {
            throw new DuplicateException();
        }

        Optional<Long> tel = memberRepository.existTel(dto.getTel());
        if (tel.isPresent()) {
            throw new DuplicateException();
        }

        Optional<Long> email = memberRepository.existEmail(dto.getEmail());
        if (email.isPresent()) {
            throw new DuplicateException();
        }

        Optional<Long> nickname = memberRepository.existNickname(dto.getNickname());
        if (nickname.isPresent()) {
            throw new DuplicateException();
        }

        Member member = Member.builder()
                .loginId(dto.getLoginId())
                .loginPw(dto.getLoginPw())
                .name(dto.getName())
                .tel(dto.getTel())
                .email(dto.getEmail())
                .birth(dto.getBirth())
                .gender(dto.getGender())
                .nickname(dto.getNickname())
                .uploadFile(dto.getUploadFile())
                .active(ACTIVE)
                .roles(Collections.singletonList("MEMBER"))
                .build();

        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }
}
