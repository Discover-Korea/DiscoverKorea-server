package com.ssafy.discoverkorea.client.member.service;

import com.ssafy.discoverkorea.jwt.TokenInfo;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AccountService {

    TokenInfo login(String loginId, String loginPw);
}
