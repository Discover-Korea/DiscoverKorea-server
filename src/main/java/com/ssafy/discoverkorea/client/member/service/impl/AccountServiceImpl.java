package com.ssafy.discoverkorea.client.member.service.impl;

import com.ssafy.discoverkorea.client.member.repository.MemberRepository;
import com.ssafy.discoverkorea.client.member.service.AccountService;
import com.ssafy.discoverkorea.jwt.JwtTokenProvider;
import com.ssafy.discoverkorea.jwt.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    @Override
    public TokenInfo login(String loginId, String loginPw) {
        //loginId, loginPw를 기반으로 Authentication 객체 생성
        //authentication은 인증 여부를 확인하는 Authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginId, loginPw);

        //실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        //authenticate 메서드가 실행될 때 CustomUserDetailsService에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        //인증 정보를 기반은 JWT 토큰 생성
        return jwtTokenProvider.generateToken(authentication);
    }

    @Override
    public String forgotLoginId(String name, String tel) {
        Optional<String> loginId = memberRepository.findByNameAndTel(name, tel);
        if (!loginId.isPresent()) {
            throw new NoSuchElementException();
        }
        return loginId.get();
    }

    @Override
    public String forgotLoginPw(String loginId, String name, String tel) {
        Optional<String> loginPw = memberRepository.forgotLoginPw(loginId, name, tel);
        if (!loginPw.isPresent()) {
            throw new NoSuchElementException();
        }
        return loginPw.get();
    }
}
