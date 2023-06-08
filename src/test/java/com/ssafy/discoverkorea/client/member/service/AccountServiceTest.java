package com.ssafy.discoverkorea.client.member.service;

import com.ssafy.discoverkorea.client.member.Member;
import com.ssafy.discoverkorea.client.member.repository.MemberRepository;
import com.ssafy.discoverkorea.jwt.TokenInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static com.ssafy.discoverkorea.client.member.Gender.MALE;
import static com.ssafy.discoverkorea.common.entity.Active.ACTIVE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class AccountServiceTest {

    @Autowired
    private AccountService accountService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("로그인#존재하지않는아이디")
    void notExistLoginId() {
        //given

        //when
        String loginId = "loginId";
        String loginPw = "loginPw1234!";

        //then
        assertThatThrownBy(() -> accountService.login(loginId, loginPw))
                .isInstanceOf(BadCredentialsException.class);
    }

    @Test
    @DisplayName("로그인#비밀번호불일치")
    void notEqualLoginPw() {
        //given
        Member member = insertMember();

        //when
        String loginPw = member.getLoginPw() + "!";

        //then
        assertThatThrownBy(() -> accountService.login(member.getLoginId(), loginPw))
                .isInstanceOf(BadCredentialsException.class);
    }

    @Test
    @DisplayName("로그인")
    void login() {
        //given
        Member member = insertMember();

        //when
        TokenInfo tokenInfo = accountService.login(member.getLoginId(), member.getLoginPw());

        //then
        assertThat(tokenInfo).isNotNull();
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