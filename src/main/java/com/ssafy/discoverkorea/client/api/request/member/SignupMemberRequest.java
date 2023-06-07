package com.ssafy.discoverkorea.client.api.request.member;

import com.ssafy.discoverkorea.client.member.Gender;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class SignupMemberRequest {

    @NotBlank
    @Size(min = 5, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @ApiModelProperty(example = "ssafy")
    private String loginId;
    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    @ApiModelProperty(example = "ssafy1234!")
    private String loginPw;
    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = "^[가-힣]*$")
    @ApiModelProperty(example = "김싸피")
    private String name;
    @NotBlank
    @Size(min = 13, max = 13)
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$")
    @ApiModelProperty(example = "010-1234-1234")
    private String tel;
    @NotBlank
    @Size(max = 50)
    @Email
    @ApiModelProperty(example = "ssafy@ssafy.com")
    private String email;
    @NotBlank
    @Size(min = 4, max = 4)
    @Pattern(regexp = "^[0-9]*$")
    @ApiModelProperty(example = "1998")
    private String birth;
    @NotNull
    @ApiModelProperty(example = "MALE")
    private Gender gender;
    @NotBlank
    @Size(max = 10)
    @Pattern(regexp = "^[a-zA-Z가-힣0-9]*$")
    @ApiModelProperty(example = "동팔이")
    private String nickname;
}
