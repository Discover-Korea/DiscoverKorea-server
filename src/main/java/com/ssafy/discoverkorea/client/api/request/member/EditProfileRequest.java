package com.ssafy.discoverkorea.client.api.request.member;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EditProfileRequest {

    private MultipartFile file;
}
