package com.ssafy.discoverkorea.client.member.service.dto;

import com.ssafy.discoverkorea.client.member.Gender;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Data
@NoArgsConstructor(access = PROTECTED)
public class SignupMemberDto {

    private String loginId;
    private String loginPw;
    private String name;
    private String tel;
    private String email;
    private String birth;
    private Gender gender;
    private String nickname;

    @Builder
    public SignupMemberDto(String loginId, String loginPw, String name, String tel, String email, String birth, Gender gender, String nickname) {
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.birth = birth;
        this.gender = gender;
        this.nickname = nickname;
    }
}
