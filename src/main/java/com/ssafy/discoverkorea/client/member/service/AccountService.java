package com.ssafy.discoverkorea.client.member.service;

import com.ssafy.discoverkorea.jwt.TokenInfo;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AccountService {

    TokenInfo login(String loginId, String loginPw);

    String forgotLoginId(String name, String tel);

    String forgotLoginPw(String loginId, String name, String tel);
}
