package com.ssafy.discoverkorea.jwt;

import com.ssafy.discoverkorea.admin.admin.Admin;
import com.ssafy.discoverkorea.admin.admin.repository.AdminRepository;
import com.ssafy.discoverkorea.client.member.Member;
import com.ssafy.discoverkorea.client.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        if (findMember.isPresent()) {
            return findMember.map(this::createMemberUserDetails)
                    .orElseThrow(() -> new UsernameNotFoundException("해당하는 사용자를 찾을 수 없습니다."));
        }
        return adminRepository.findByLoginId(loginId)
                .map(this::createAdminUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 관리자를 찾을 수 없습니다."));
    }

    //해당하는 User의 데이터가 존재하면 UserDetails 객체를 만들어서 반환
    private UserDetails createMemberUserDetails(Member member) {
        return User.builder()
                .username(member.getUsername())
                .password(passwordEncoder.encode(member.getPassword()))
                .roles(member.getRoles().toArray(new String[0]))
                .build();
    }

    private UserDetails createAdminUserDetails(Admin admin) {
        return User.builder()
                .username(admin.getUsername())
                .password(passwordEncoder.encode(admin.getPassword()))
                .roles(admin.getRoles().toArray(new String[0]))
                .build();
    }
}
