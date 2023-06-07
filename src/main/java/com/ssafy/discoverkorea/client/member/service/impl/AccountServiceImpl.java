package com.ssafy.discoverkorea.client.member.service.impl;

import com.ssafy.discoverkorea.client.member.service.AccountService;
import com.ssafy.discoverkorea.jwt.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Override
    public TokenInfo login(String loginId, String loginPw) {
        return null;
    }
}
