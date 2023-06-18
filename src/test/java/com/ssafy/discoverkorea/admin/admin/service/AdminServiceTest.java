package com.ssafy.discoverkorea.admin.admin.service;

import com.ssafy.discoverkorea.admin.admin.Admin;
import com.ssafy.discoverkorea.admin.admin.repository.AdminRepository;
import com.ssafy.discoverkorea.admin.admin.service.dto.EditLoginPwDto;
import com.ssafy.discoverkorea.admin.admin.service.dto.RegisterAdminDto;
import com.ssafy.discoverkorea.common.exception.DuplicateException;
import com.ssafy.discoverkorea.common.exception.EditException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

import static com.ssafy.discoverkorea.common.entity.Active.ACTIVE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class AdminServiceTest {

    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRepository adminRepository;

    @Test
    @DisplayName("관리자 등록#아이디 중복")
    void duplicationLoginId() {
        //given
        Admin admin = insertAdmin();

        //when
        RegisterAdminDto dto = RegisterAdminDto.builder()
                .loginId(admin.getLoginId())
                .build();

        //then
        assertThatThrownBy(() -> adminService.register(dto))
                .isInstanceOf(DuplicateException.class);
    }

    @Test
    @DisplayName("관리자 등록#연락처 중복")
    void duplicationTel() {
        //given
        Admin admin = insertAdmin();

        //when
        RegisterAdminDto dto = RegisterAdminDto.builder()
                .tel(admin.getTel())
                .build();

        //then
        assertThatThrownBy(() -> adminService.register(dto))
                .isInstanceOf(DuplicateException.class);
    }

    @Test
    @DisplayName("관리자 등록#이메일 중복")
    void duplicationEmail() {
        //given
        Admin admin = insertAdmin();

        //when
        RegisterAdminDto dto = RegisterAdminDto.builder()
                .email(admin.getEmail())
                .build();

        //then
        assertThatThrownBy(() -> adminService.register(dto))
                .isInstanceOf(DuplicateException.class);
    }

    @Test
    @DisplayName("관리자 등록")
    void register() {
        //given
        RegisterAdminDto dto = RegisterAdminDto.builder()
                .loginId("admin")
                .loginPw("admin1234!")
                .name("관리자")
                .tel("010-8765-4321")
                .email("admin@ssafy.com")
                .build();

        //when
        Long adminId = adminService.register(dto);

        //then
        Optional<Admin> findAdmin = adminRepository.findById(adminId);
        assertThat(findAdmin).isPresent();
    }

    @Test
    @DisplayName("비밀번호 변경")
    void editLoginPw() {
        //given
        Admin admin = insertAdmin();
        EditLoginPwDto dto = EditLoginPwDto.builder()
                .nowLoginPw(admin.getLoginPw())
                .newLoginPw(admin.getLoginPw() + "@")
                .build();

        //when
        Long adminId = adminService.editLoginPw(admin.getLoginId(), dto);

        //then
        Optional<Admin> findAdmin = adminRepository.findById(adminId);
        assertThat(findAdmin).isPresent();
        assertThat(findAdmin.get().getLoginPw()).isEqualTo(dto.getNewLoginPw());
    }

    @Test
    @DisplayName("연락처 변경#기존 연락처와 일치")
    void equalBeforeTel() {
        //given
        Admin admin = insertAdmin();

        //when

        //then
        assertThatThrownBy(() -> adminService.editTel(admin.getLoginId(), admin.getTel()))
                .isInstanceOf(EditException.class);
    }

    @Test
    @DisplayName("연락처 변경#연락처 중복")
    void duplicationNewTel() {
        //given
        Admin admin = insertAdmin();
        Admin targetAdmin = adminRepository.save(Admin.builder()
                .loginId("admin1")
                .loginPw("admin1234!")
                .name("관리자1")
                .tel("010-8765-8765")
                .email("admin1@ssafy.com")
                .active(ACTIVE)
                .roles(Collections.singletonList("ADMIN"))
                .build());
        //when

        //then
        assertThatThrownBy(() -> adminService.editTel(admin.getLoginId(), targetAdmin.getTel()))
                .isInstanceOf(EditException.class);
    }

    @Test
    @DisplayName("연락처 변경")
    void editTel() {
        //given
        Admin admin = insertAdmin();
        String newTel = admin.getTel().replace("4321", "1111");

        //when
        Long adminId = adminService.editTel(admin.getLoginId(), newTel);

        //then
        Optional<Admin> findAdmin = adminRepository.findById(adminId);
        assertThat(findAdmin).isPresent();
        assertThat(findAdmin.get().getTel()).isEqualTo(newTel);
    }

    @Test
    @DisplayName("이메일 변경#기존 이메일과 일치")
    void equalBeforeEmail() {
        //given
        Admin admin = insertAdmin();


        //when

        //then
        assertThatThrownBy(() -> adminService.editEmail(admin.getLoginId(), admin.getEmail()))
                .isInstanceOf(EditException.class);
    }

    @Test
    @DisplayName("이메일 변경#이메일 중복")
    void duplicationNewEmail() {
        //given
        Admin admin = insertAdmin();
        Admin targetAdmin = adminRepository.save(Admin.builder()
                .loginId("admin1")
                .loginPw("admin1234!")
                .name("관리자1")
                .tel("010-8765-8765")
                .email("admin1@ssafy.com")
                .active(ACTIVE)
                .roles(Collections.singletonList("ADMIN"))
                .build());

        //when

        //then
        assertThatThrownBy(() -> adminService.editEmail(admin.getLoginId(), targetAdmin.getEmail()))
                .isInstanceOf(EditException.class);
    }

    @Test
    @DisplayName("이메일 변경")
    void editEmail() {
        //given
        Admin admin = insertAdmin();
        String newEmail = admin.getEmail().replace("admin@", "admin1@");

        //when
        Long adminId = adminService.editEmail(admin.getLoginId(), newEmail);

        //then
        Optional<Admin> findAdmin = adminRepository.findById(adminId);
        assertThat(findAdmin).isPresent();
        assertThat(findAdmin.get().getEmail()).isEqualTo(newEmail);
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