package com.ssafy.discoverkorea.admin.admin.service.impl;

import com.ssafy.discoverkorea.admin.admin.Admin;
import com.ssafy.discoverkorea.admin.admin.repository.AdminRepository;
import com.ssafy.discoverkorea.admin.admin.service.AdminService;
import com.ssafy.discoverkorea.admin.admin.service.dto.EditLoginPwDto;
import com.ssafy.discoverkorea.admin.admin.service.dto.RegisterAdminDto;
import com.ssafy.discoverkorea.common.exception.DuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

import static com.ssafy.discoverkorea.common.entity.Active.ACTIVE;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public Long register(RegisterAdminDto dto) {
        Optional<Long> existLoginId = adminRepository.existLoginId(dto.getLoginId());
        if (existLoginId.isPresent()) {
            throw new DuplicateException();
        }

        Optional<Long> existTel = adminRepository.existTel(dto.getTel());
        if (existTel.isPresent()) {
            throw new DuplicateException();
        }

        Optional<Long> existEmail = adminRepository.existEmail(dto.getEmail());
        if (existEmail.isPresent()) {
            throw new DuplicateException();
        }

        Admin admin = Admin.builder()
                .loginId(dto.getLoginId())
                .loginPw(dto.getLoginPw())
                .name(dto.getName())
                .tel(dto.getTel())
                .email(dto.getEmail())
                .active(ACTIVE)
                .roles(Collections.singletonList("ADMIN"))
                .build();
        Admin savedAdmin = adminRepository.save(admin);
        return savedAdmin.getId();
    }

    @Override
    public Long editLoginPw(String loginId, EditLoginPwDto dto) {
        return null;
    }
}
