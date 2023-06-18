package com.ssafy.discoverkorea.admin.api.request.admin;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class EditEmailRequest {

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
}