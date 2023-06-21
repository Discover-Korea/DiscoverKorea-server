package com.ssafy.discoverkorea.client.member.repository;

import com.ssafy.discoverkorea.client.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static com.ssafy.discoverkorea.client.member.Gender.MALE;
import static com.ssafy.discoverkorea.common.entity.Active.ACTIVE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberQueryRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberQueryRepository memberQueryRepository;

    @Test
    @DisplayName("아이디 중복 조회")
    void existLoginId() {
        //given
        Member member = insertMember();

        //when
        Boolean result = memberQueryRepository.existLoginId(member.getLoginId());

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("연락처 중복 검사")
    void existTel() {
        //given
        Member member = insertMember();

        //when
        Boolean result = memberQueryRepository.existTel(member.getTel());

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("이메일 중복 검사")
    void existEmail() {
        //given
        Member member = insertMember();

        //when
        Boolean result = memberQueryRepository.existEmail(member.getEmail());

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("닉네임 중복 검사")
    void existNickname() {
        //given
        Member member = insertMember();

        //when
        Boolean result = memberQueryRepository.existNickname(member.getNickname());

        //then
        assertThat(result).isTrue();
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
                .nickname("동팔이")
                .active(ACTIVE)
                .roles(Collections.singletonList("MEMBER"))
                .build();
        return memberRepository.save(member);
    }
}