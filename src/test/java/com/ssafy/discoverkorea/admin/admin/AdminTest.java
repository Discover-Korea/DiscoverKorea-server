package com.ssafy.discoverkorea.admin.admin;

import com.ssafy.discoverkorea.common.exception.EditException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AdminTest {

    @Test
    @DisplayName("비밀번호 변경#현재 비밀번호 불일치")
    void notEqualNowLoginPw() {
        //given
        Admin admin = Admin.builder()
                .loginPw("admin1234!")
                .build();

        //when
        String nowLoginPw = admin.getLoginPw() + "@";
        String newLoginPw = admin.getLoginPw() + "@";

        //then
        assertThatThrownBy(() -> admin.editLoginPw(nowLoginPw, newLoginPw))
                .isInstanceOf(EditException.class);
    }

    @Test
    @DisplayName("비밀번호 변경#변경할 비밀번호가 현재 비밀번호와 일치")
    void notChangeLoginPw() {
        //given
        Admin admin = Admin.builder()
                .loginPw("admin1234!")
                .build();

        //when
        String nowLoginPw = admin.getLoginPw();
        String newLoginPw = admin.getLoginPw();

        //then
        assertThatThrownBy(() -> admin.editLoginPw(nowLoginPw, newLoginPw))
                .isInstanceOf(EditException.class);
    }

    @Test
    @DisplayName("비밀번호 변경")
    void editLoginPw() {
        //given
        Admin admin = Admin.builder()
                .loginPw("admin1234!")
                .build();

        //when
        String nowLoginPw = admin.getLoginPw();
        String newLoginPw = admin.getLoginPw() + "!";
        admin.editLoginPw(nowLoginPw, newLoginPw);

        //then
        assertThat(admin.getLoginPw()).isEqualTo(newLoginPw);
    }

    @Test
    @DisplayName("연락처 변경")
    void editTel() {
        //given
        Admin admin = Admin.builder()
                .tel("010-8765-4321")
                .build();

        //when
        String newTel = admin.getTel().replace("4321", "1111");
        admin.editTel(newTel);

        //then
        assertThat(admin.getTel()).isEqualTo(newTel);
    }

    @Test
    @DisplayName("이메일 변경")
    void editEmail() {
        //given
        Admin admin = Admin.builder()
                .email("ssafy@ssafy.com")
                .build();

        //when
        String newEmail = admin.getEmail().replace("ssafy@", "ssafy1@");
        admin.editEmail(newEmail);

        //then
        assertThat(admin.getEmail()).isEqualTo(newEmail);
    }
}