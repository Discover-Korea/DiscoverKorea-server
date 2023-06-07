package com.ssafy.discoverkorea.client.member.service;

import com.ssafy.discoverkorea.client.member.service.dto.SignupMemberDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {

    Long signup(SignupMemberDto dto);
}
