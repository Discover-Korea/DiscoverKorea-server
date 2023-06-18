package com.ssafy.discoverkorea.admin.admin.service;

import com.ssafy.discoverkorea.admin.admin.Admin;
import com.ssafy.discoverkorea.admin.admin.repository.AdminRepository;
import com.ssafy.discoverkorea.jwt.TokenInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static com.ssafy.discoverkorea.common.entity.Active.ACTIVE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class AdminAccountServiceTest {

    @Autowired
    private AdminAccountService adminAccountService;
    @Autowired
    private AdminRepository adminRepository;

    @Test
    @DisplayName("로그인#존재하지 않는 아이디")
    void notExistLoginId() {
        //given

        //when
        String loginId = "loginId";
        String loginPw = "loginPw1234!";

        //then
        assertThatThrownBy(() -> adminAccountService.login(loginId, loginPw))
                .isInstanceOf(BadCredentialsException.class);
    }

    @Test
    @DisplayName("로그인#비밀번호 불일치")
    void notEqualLoginPw() {
        //given
        Admin admin = insertAdmin();

        //when
        String loginPw = admin.getLoginPw() + "!";

        //then
        assertThatThrownBy(() -> adminAccountService.login(admin.getLoginId(), loginPw))
                .isInstanceOf(BadCredentialsException.class);
    }

    @Test
    @DisplayName("로그인")
    void login() {
        //given
        Admin admin = insertAdmin();

        //when
        TokenInfo tokenInfo = adminAccountService.login(admin.getLoginId(), admin.getLoginPw());

        //then
        assertThat(tokenInfo).isNotNull();
    }

    private Admin insertAdmin() {
        Admin admin = Admin.builder()
                .loginId("admin")
                .loginPw("admin1234!")
                .name("관리자")
                .tel("010-8765-4321")
                .email("admin@ssafy.com")
                .active(ACTIVE)
                .roles(Collections.singletonList("ADMIN"))
                .build();
        return adminRepository.save(admin);
    }
}