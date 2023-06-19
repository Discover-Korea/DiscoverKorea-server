package com.ssafy.discoverkorea.admin.admin.service;

import com.ssafy.discoverkorea.jwt.TokenInfo;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AdminAccountService {

    TokenInfo login(String loginId, String loginPw);
}
