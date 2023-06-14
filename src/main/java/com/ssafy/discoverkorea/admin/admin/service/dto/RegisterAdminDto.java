package com.ssafy.discoverkorea.admin.admin.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class RegisterAdminDto {

    private String loginId;
    private String loginPw;
    private String name;
    private String tel;
    private String email;

    @Builder
    public RegisterAdminDto(String loginId, String loginPw, String name, String tel, String email) {
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
        this.tel = tel;
        this.email = email;
    }
}
