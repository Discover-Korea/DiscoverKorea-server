package com.ssafy.discoverkorea.client.member.service;

import com.ssafy.discoverkorea.client.member.Member;
import com.ssafy.discoverkorea.client.member.repository.MemberRepository;
import com.ssafy.discoverkorea.client.member.service.dto.SignupMemberDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.ssafy.discoverkorea.client.member.Gender.*;
import static com.ssafy.discoverkorea.common.entity.Active.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입#아이디중복")
    void duplicationLoginId() {
        //given
        Member targetMember = insertMember();

        //when
        SignupMemberDto dto = SignupMemberDto.builder()
                .loginId(targetMember.getLoginId())
                .build();

        //then
        assertThatThrownBy(() -> memberService.signup(dto))
                .isInstanceOf(DuplicateException.class);
    }

    @Test
    @DisplayName("회원가입#연락처중복")
    void duplicationTel() {
        //given
        Member targetMember = insertMember();

        //when
        SignupMemberDto dto = SignupMemberDto.builder()
                .tel(targetMember.getTel())
                .build();

        //then
        assertThatThrownBy(() -> memberService.signup(dto))
                .isInstanceOf(DuplicateException.class);
    }

    @Test
    @DisplayName("회원가입#이메일중복")
    void duplicationEmail() {
        //given
        Member targetMember = insertMember();

        //when
        SignupMemberDto dto = SignupMemberDto.builder()
                .email(targetMember.getEmail())
                .build();

        //then
        assertThatThrownBy(() -> memberService.signup(dto))
                .isInstanceOf(DuplicateException.class);
    }

    @Test
    @DisplayName("회원가입#닉네임중복")
    void duplicationNickname() {
        //given
        Member targetMember = insertMember();

        //when
        SignupMemberDto dto = SignupMemberDto.builder()
                .nickname(targetMember.getNickname())
                .build();

        //then
        assertThatThrownBy(() -> memberService.signup(dto))
                .isInstanceOf(DuplicateException.class);
    }

    @Test
    @DisplayName("회원가입")
    void signup() {
        //given
        SignupMemberDto dto = SignupMemberDto.builder()
                .loginId("ssafy")
                .loginPw("ssafy1234!")
                .name("김싸피")
                .tel("010-1234-1234")
                .email("ssafy@ssafy.com")
                .birth("1998")
                .gender(MALE)
                .nickname("spring")
                .build();

        //when
        Long memberId = memberService.signup(dto);

        //then
        Optional<Member> findMember = memberRepository.findById(memberId);
        assertThat(findMember).isPresent();
    }

    private Member insertMember() {
        Member member = Member.builder()
                .loginId("ssafy")
                .loginPw("ssafy1234!")
                .name("김싸피")
                .tel("010-1234-1234")
                .email("ssafy@ssafy.com")
                .birth("1998")
                .gender(MALE)
                .nickname("spring")
                .active(ACTIVE)
                .build();
        return memberRepository.save(member);
    }
}