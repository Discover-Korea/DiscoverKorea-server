package com.ssafy.discoverkorea.client.member;

import com.ssafy.discoverkorea.common.entity.Active;
import com.ssafy.discoverkorea.common.entity.TimeBaseEntity;
import com.ssafy.discoverkorea.common.entity.UploadFile;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.EnumType.*;

@Entity
@Getter
public class Member extends TimeBaseEntity {

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

    //== 생성자 ==//
    public Member() {
    }

    @Builder
    public Member(Long id, String loginId, String loginPw, String name, String tel, String email, String birth, Gender gender, String nickname, UploadFile uploadFile, Active active) {
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
    }
}
