package com.ssafy.discoverkorea.client.member.service;

import com.ssafy.discoverkorea.client.member.service.dto.EditEmailDto;
import com.ssafy.discoverkorea.client.member.service.dto.EditLoginPwDto;
import com.ssafy.discoverkorea.client.member.service.dto.SignupMemberDto;
import com.ssafy.discoverkorea.common.entity.UploadFile;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {

    Long signup(SignupMemberDto dto);

    Long editLoginPw(String loginId, EditLoginPwDto dto);

    Long editTel(String loginId, String newTel);

    Long editEmail(String loginId, EditEmailDto dto);

    Long editNickname(String loginId, String newNickname);

    Long editProfile(String loginId, UploadFile uploadFile);

    Long withdrawal(String loginId, String loginPw);
}
