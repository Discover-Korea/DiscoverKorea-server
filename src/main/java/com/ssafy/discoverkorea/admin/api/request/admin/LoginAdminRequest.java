package com.ssafy.discoverkorea.admin.api.request.admin;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginAdminRequest {

    @NotBlank
    private String loginId;
    @NotBlank
    private String loginPw;
}
