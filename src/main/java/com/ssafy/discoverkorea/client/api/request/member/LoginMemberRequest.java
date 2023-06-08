package com.ssafy.discoverkorea.client.api.request.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginMemberRequest {

    @NotBlank
    @ApiModelProperty(example = "ssafy")
    private String loginId;
    @NotBlank
    @ApiModelProperty(example = "ssafy1234!")
    private String loginPw;
}
