package com.ssafy.discoverkorea.client.api.request.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class EditEmailRequest {

    @NotBlank
    @Size(max = 50)
    @Email
    @ApiModelProperty(example = "ssafy1@ssafy.com")
    private String newEmail;
}
