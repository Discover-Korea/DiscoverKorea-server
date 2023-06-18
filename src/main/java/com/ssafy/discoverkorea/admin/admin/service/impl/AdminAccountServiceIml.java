package com.ssafy.discoverkorea.admin.admin.service.impl;

import com.ssafy.discoverkorea.admin.admin.repository.AdminRepository;
import com.ssafy.discoverkorea.admin.admin.service.AdminAccountService;
import com.ssafy.discoverkorea.jwt.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminAccountServiceIml implements AdminAccountService {

    private final AdminRepository adminRepository;

    @Override
    public TokenInfo login(String loginId, String loginPw) {
        return null;
    }
}
