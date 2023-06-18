package com.ssafy.discoverkorea.admin.admin.service;

import com.ssafy.discoverkorea.admin.admin.service.dto.EditLoginPwDto;
import com.ssafy.discoverkorea.admin.admin.service.dto.RegisterAdminDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AdminService {

    Long register(RegisterAdminDto dto);

    Long editLoginPw(String loginId, EditLoginPwDto dto);

    Long editTel(String loginId, String newTel);

    Long editEmail(String loginId, String newEmail);

    Long remove(Long adminId);
}
