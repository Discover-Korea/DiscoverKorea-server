package com.ssafy.discoverkorea.admin.admin.service;

import com.ssafy.discoverkorea.admin.admin.service.dto.RegisterAdminDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AdminService {

    Long register(RegisterAdminDto dto);
}
