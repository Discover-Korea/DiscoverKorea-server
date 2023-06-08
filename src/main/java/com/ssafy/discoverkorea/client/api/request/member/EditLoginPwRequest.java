package com.ssafy.discoverkorea.client.api.request.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class EditLoginPwRequest {

    @NotBlank
    @ApiModelProperty(example = "ssafy1234!")
    private String nowLoginPw;
    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    @ApiModelProperty(example = "ssafy1234!@")
    private String newLoginPw;
    @NotBlank
    @ApiModelProperty(example = "ssafy1234!@")
    private String confLoginPw;
}
