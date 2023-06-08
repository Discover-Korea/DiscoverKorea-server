package com.ssafy.discoverkorea.client.member;

import com.ssafy.discoverkorea.common.exception.EditException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberTest {
    
    @Test
    @DisplayName("비밀번호 변경#현재 비밀번호 불일치")
    void notEqualNowLoginPw() {
        //given
        Member member = Member.builder()
                .loginPw("ssafy1234!")
                .build();

        //when
        String nowLoginPw = member.getLoginPw() + "@";
        String newLoginPw = member.getLoginPw() + "@";
            
        //then
        assertThatThrownBy(() -> member.editLoginPw(nowLoginPw, newLoginPw))
                .isInstanceOf(EditException.class);
    }

    @Test
    @DisplayName("비밀번호 변경#변경할 비밀번호가 현재 비밀번호와 일치")
    void notChangeLoginPw() {
        //given
        Member member = Member.builder()
                .loginPw("ssafy1234!")
                .build();

        //when
        String nowLoginPw = member.getLoginPw();
        String newLoginPw = member.getLoginPw();

        //then
        assertThatThrownBy(() -> member.editLoginPw(nowLoginPw, newLoginPw))
                .isInstanceOf(EditException.class);
    }

    @Test
    @DisplayName("비밀번호 변경")
    void editLoginPw() {
        //given
        Member member = Member.builder()
                .loginPw("ssafy1234!")
                .build();

        //when
        String nowLoginPw = member.getLoginPw();
        String newLoginPw = member.getLoginPw() + "!";
        member.editLoginPw(nowLoginPw, newLoginPw);

        //then
        assertThat(member.getLoginPw()).isEqualTo(newLoginPw);
    }

    @Test
    @DisplayName("연락처 변경")
    void editTel() {
        //given
        Member member = Member.builder()
                .tel("010-1234-1234")
                .build();
        String newTel = member.getTel().replace("1234", "5678");

        //when
        member.editTel(newTel);

        //then
        assertThat(member.getTel()).isEqualTo(newTel);
    }

    @Test
    @DisplayName("이메일 변경")
    void editEmail() {
        //given
        Member member = Member.builder()
                .email("ssafy@ssafy.com")
                .build();
        String newEmail = member.getEmail().replace("ssafy@", "ssafy1@");

        //when
        member.editEmail(newEmail);
            
        //then
        assertThat(member.getEmail()).isEqualTo(newEmail);
    }
}