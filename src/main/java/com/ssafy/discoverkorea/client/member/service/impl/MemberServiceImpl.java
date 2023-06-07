package com.ssafy.discoverkorea.client.member.service.impl;

import com.ssafy.discoverkorea.client.member.service.MemberService;
import com.ssafy.discoverkorea.client.member.service.dto.SignupMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    @Override
    public Long signup(SignupMemberDto dto) {
        return null;
    }
}
