package com.ssafy.discoverkorea.client.api.request.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class EditNicknameRequest {

    @NotBlank
    @Size(max = 10)
    @Pattern(regexp = "^[a-zA-Z가-힣0-9]*$")
    @ApiModelProperty(example = "두칠이")
    private String newNickname;
}
