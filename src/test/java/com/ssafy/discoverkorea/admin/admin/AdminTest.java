package com.ssafy.discoverkorea.admin.admin;

import com.ssafy.discoverkorea.common.exception.EditException;
import org.assertj.core.api.Assertions;
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
}