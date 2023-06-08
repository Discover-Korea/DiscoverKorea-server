package com.ssafy.discoverkorea.client.member.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class EditLoginPwDto {

    private String nowLoginPw;
    private String newLoginPW;

    @Builder
    public EditLoginPwDto(String nowLoginPw, String newLoginPW) {
        this.nowLoginPw = nowLoginPw;
        this.newLoginPW = newLoginPW;
    }
}
