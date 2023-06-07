package com.ssafy.discoverkorea.client.member;

import com.ssafy.discoverkorea.common.entity.Active;
import com.ssafy.discoverkorea.common.entity.TimeBaseEntity;
import com.ssafy.discoverkorea.common.entity.UploadFile;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Member extends TimeBaseEntity implements UserDetails {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    @Column(unique = true, nullable = false, updatable = false, length = 20)
    private String loginId;
    @Column(nullable = false, length = 20)
    private String loginPw;
    @Column(nullable = false, updatable = false, length = 20)
    private String name;
    @Column(unique = true, nullable = false, length = 13)
    private String tel;
    @Column(unique = true, nullable = false, length = 50)
    private String email;
    @Column(nullable = false, updatable = false, length = 4)
    private String birth;
    @Enumerated(STRING)
    @Column(nullable = false, updatable = false, length = 20)
    private Gender gender;
    @Column(unique = true, nullable = false, length = 10)
    private String nickname;
    @Embedded
    private UploadFile uploadFile;
    @Enumerated(STRING)
    @Column(nullable = false, length = 20)
    private Active active;

    @ElementCollection(fetch = EAGER)
    private List<String> roles = new ArrayList<>();

    //== 생성자 ==//
    public Member() {
    }

    @Builder
    public Member(Long id, String loginId, String loginPw, String name, String tel, String email, String birth, Gender gender, String nickname, UploadFile uploadFile, Active active, List<String> roles) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.birth = birth;
        this.gender = gender;
        this.nickname = nickname;
        this.uploadFile = uploadFile;
        this.active = active;
        this.roles = roles;
    }

    //== 시큐리티 설정 ==//
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    @Override
    public String getPassword() {
        return loginPw;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
