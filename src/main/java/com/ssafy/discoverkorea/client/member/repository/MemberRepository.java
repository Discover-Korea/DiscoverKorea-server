package com.ssafy.discoverkorea.client.member.repository;

import com.ssafy.discoverkorea.client.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginId(@Param("loginId") String loginId);

    @Query("select m.loginId from Member m where m.name=:name and m.tel=:tel")
    Optional<String> findByNameAndTel(@Param("name") String name, @Param("tel") String tel);

    @Query("select m.loginPw from Member m where m.loginId=:loginId and m.name=:name and m.tel=:tel")
    Optional<String> forgotLoginPw(@Param("loginId") String loginId, @Param("name") String name, @Param("tel") String tel);
}
