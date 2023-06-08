package com.ssafy.discoverkorea.client.api.request.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class WithdrawalMemberRequest {

    @NotBlank
    private String loginPw;
}
