package com.ssafy.discoverkorea.client.member.service;

import com.ssafy.discoverkorea.client.member.Member;
import com.ssafy.discoverkorea.client.member.repository.MemberRepository;
import com.ssafy.discoverkorea.client.member.service.dto.EditEmailDto;
import com.ssafy.discoverkorea.client.member.service.dto.EditLoginPwDto;
import com.ssafy.discoverkorea.client.member.service.dto.SignupMemberDto;
import com.ssafy.discoverkorea.common.entity.UploadFile;
import com.ssafy.discoverkorea.common.exception.DuplicateException;
import com.ssafy.discoverkorea.common.exception.EditException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.ssafy.discoverkorea.client.member.Gender.MALE;
import static com.ssafy.discoverkorea.common.entity.Active.ACTIVE;
import static com.ssafy.discoverkorea.common.entity.Active.DEACTIVE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    @DisplayName("비밀번호 변경")
    void editLoginPw() {
        //given
        Member member = insertMember();
        String newLoginPw = member.getLoginPw() + "@";

        EditLoginPwDto dto = EditLoginPwDto.builder()
                .nowLoginPw(member.getLoginPw())
                .newLoginPw(newLoginPw)
                .build();

        //when
        Long memberId = memberService.editLoginPw(member.getLoginId(), dto);

        //then
        Optional<Member> findMember = memberRepository.findById(memberId);
        assertThat(findMember).isPresent();
        assertThat(findMember.get().getLoginPw()).isEqualTo(newLoginPw);
    }

    @Test
    @DisplayName("연락처 변경#기존 연락처와 일치")
    void equalBeforeTel() {
        //given
        Member member = insertMember();

        //when

        //then
        assertThatThrownBy(() -> memberService.editTel(member.getLoginId(), member.getTel()))
                .isInstanceOf(EditException.class);
    }

    @Test
    @DisplayName("연락처 변경#연락처 중복")
    void duplicationNewTel() {
        //given
        Member targetMember = memberRepository.save(Member.builder()
                .loginId("ssafy1")
                .loginPw("ssafy1234!")
                .name("김싸피")
                .tel("010-5678-5678")
                .email("ssafy1@ssafy.com")
                .birth("1998")
                .gender(MALE)
                .nickname("react")
                .active(ACTIVE)
                .build());
        Member member = insertMember();

        //when

        //then
        assertThatThrownBy(() -> memberService.editTel(member.getLoginId(), targetMember.getTel()))
                .isInstanceOf(EditException.class);
    }

    @Test
    @DisplayName("연락처 변경")
    void editTel() {
        //given
        Member member = insertMember();
        String newTel = member.getTel().replace("1234", "5678");

        //when
        Long memberId = memberService.editTel(member.getLoginId(), newTel);

        //then
        Optional<Member> findMember = memberRepository.findById(memberId);
        assertThat(findMember).isPresent();
        assertThat(findMember.get().getTel()).isEqualTo(newTel);
    }

    @Test
    @DisplayName("이메일 변경#기존 이메일과 일치")
    void equalBeforeEmail() {
        //given
        Member member = insertMember();

        //when
        EditEmailDto dto = EditEmailDto.builder()
                .nowEmail(member.getEmail())
                .newEmail(member.getEmail())
                .build();

        //then
        assertThatThrownBy(() -> memberService.editEmail(member.getLoginId(), dto))
                .isInstanceOf(EditException.class);
    }

    @Test
    @DisplayName("이메일 변경#이메일 중복")
    void duplicationNewEmail() {
        //given
        Member targetMember = memberRepository.save(Member.builder()
                .loginId("ssafy1")
                .loginPw("ssafy1234!")
                .name("김싸피")
                .tel("010-5678-5678")
                .email("ssafy1@ssafy.com")
                .birth("1998")
                .gender(MALE)
                .nickname("react")
                .active(ACTIVE)
                .build());
        Member member = insertMember();

        //when
        EditEmailDto dto = EditEmailDto.builder()
                .nowEmail(member.getEmail())
                .newEmail(targetMember.getEmail())
                .build();

        //then
        assertThatThrownBy(() -> memberService.editEmail(member.getLoginId(), dto))
                .isInstanceOf(EditException.class);
    }

    @Test
    @DisplayName("이메일 변경")
    void editEmail() {
        //given
        Member member = insertMember();
        String newEmail = member.getEmail().replace("ssafy@", "ssafy1@");
        EditEmailDto dto = EditEmailDto.builder()
                .nowEmail(member.getEmail())
                .newEmail(newEmail)
                .build();
        //when
        Long memberId = memberService.editEmail(member.getLoginId(), dto);

        //then
        Optional<Member> findMember = memberRepository.findById(memberId);
        assertThat(findMember).isPresent();
        assertThat(findMember.get().getEmail()).isEqualTo(newEmail);
    }

    @Test
    @DisplayName("닉네임 변경#기존 닉네임과 일치")
    void equalBeforeNickname() {
        //given
        Member member = insertMember();

        //when

        //then
        assertThatThrownBy(() -> memberService.editNickname(member.getLoginId(), member.getNickname()))
                .isInstanceOf(EditException.class);
    }

    @Test
    @DisplayName("닉네임 변경#닉네임 중복")
    void duplicationNewNickname() {
        //given
        Member targetMember = memberRepository.save(Member.builder()
                .loginId("ssafy1")
                .loginPw("ssafy1234!")
                .name("김싸피")
                .tel("010-5678-5678")
                .email("ssafy1@ssafy.com")
                .birth("1998")
                .gender(MALE)
                .nickname("react")
                .active(ACTIVE)
                .build());
        Member member = insertMember();

        //when

        //then
        assertThatThrownBy(() -> memberService.editNickname(member.getLoginId(), targetMember.getNickname()))
                .isInstanceOf(EditException.class);
    }

    @Test
    @DisplayName("닉네임 변경")
    void editNickname() {
        //given
        Member member = insertMember();
        String newNickname = "동팔이";

        //when
        Long memberId = memberService.editNickname(member.getLoginId(), newNickname);

        //then
        Optional<Member> findMember = memberRepository.findById(memberId);
        assertThat(findMember).isPresent();
        assertThat(findMember.get().getNickname()).isEqualTo(newNickname);
    }

    @Test
    @DisplayName("프로필 이미지 변경")
    void editProfile() {
        //given
        Member member = insertMember();
        UploadFile newProfile = UploadFile.builder()
                .storeFileName("store_file_name.jpg")
                .uploadFileName("upload_file_name.jpg")
                .build();

        //when
        Long memberId = memberService.editProfile(member.getLoginId(), newProfile);

        //then
        Optional<Member> findMember = memberRepository.findById(memberId);
        assertThat(findMember).isPresent();
        assertThat(findMember.get().getUploadFile()).isEqualTo(newProfile);
    }

    @Test
    @DisplayName("회원탈퇴")
    void withdrawal() {
        //given
        Member member = insertMember();

        //when
        Long memberId = memberService.withdrawal(member.getLoginId(), member.getLoginPw());

        //then
        Optional<Member> findMember = memberRepository.findById(memberId);
        assertThat(findMember).isPresent();
        assertThat(findMember.get().getActive()).isEqualTo(DEACTIVE);
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