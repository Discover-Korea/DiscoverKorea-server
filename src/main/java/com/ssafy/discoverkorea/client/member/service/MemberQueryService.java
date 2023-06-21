package com.ssafy.discoverkorea.client.member.service;

import com.ssafy.discoverkorea.client.member.repository.MemberQueryRepository;
import com.ssafy.discoverkorea.common.exception.DuplicateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryService {

    private final MemberQueryRepository memberQueryRepository;

    public void existLoginId(String loginId) {
        if (memberQueryRepository.existLoginId(loginId)) {
            throw new DuplicateException();
        }
    }

    public void existTel(String tel) {
        if (memberQueryRepository.existTel(tel)) {
            throw new DuplicateException();
        }
    }

    public void existEmail(String email) {
        if (memberQueryRepository.existEmail(email)) {
            throw new DuplicateException();
        }
    }

    public void existNickname(String nickname) {
        if (memberQueryRepository.existNickname(nickname)) {
            throw new DuplicateException();
        }
    }
}
