package com.ssafy.discoverkorea.client.api.request.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ForgotLoginPwRequest {

    @NotBlank
    private String loginId;
    @NotBlank
    private String name;
    @NotBlank
    private String tel;
}
