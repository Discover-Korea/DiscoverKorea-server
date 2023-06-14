package com.ssafy.discoverkorea.admin.admin.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class EditLoginPwDto {

    private String nowLoginPw;
    private String newLoginPw;

    @Builder
    public EditLoginPwDto(String nowLoginPw, String newLoginPw) {
        this.nowLoginPw = nowLoginPw;
        this.newLoginPw = newLoginPw;
    }
}
