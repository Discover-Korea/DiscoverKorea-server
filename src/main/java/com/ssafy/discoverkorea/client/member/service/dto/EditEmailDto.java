package com.ssafy.discoverkorea.client.member.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class EditEmailDto {

    private String nowEmail;
    private String newEmail;

    @Builder
    public EditEmailDto(String nowEmail, String newEmail) {
        this.nowEmail = nowEmail;
        this.newEmail = newEmail;
    }
}
