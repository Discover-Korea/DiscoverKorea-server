package com.ssafy.discoverkorea.admin.admin.service.impl;

import com.ssafy.discoverkorea.admin.admin.repository.AdminRepository;
import com.ssafy.discoverkorea.admin.admin.service.AdminService;
import com.ssafy.discoverkorea.admin.admin.service.dto.RegisterAdminDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public Long register(RegisterAdminDto dto) {
        return null;
    }
}
