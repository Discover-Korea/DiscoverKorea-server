package com.ssafy.discoverkorea.client.member.repository;

import com.ssafy.discoverkorea.client.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m.id from Member m where m.loginId=:loginId")
    Optional<Long> existLoginId(@Param("loginId") String loginId);

    @Query("select m.id from Member m where m.tel=:tel")
    Optional<Long> existTel(@Param("tel") String tel);

    @Query("select m.id from Member m where m.email=:email")
    Optional<Long> existEmail(@Param("email") String email);

    @Query("select m.id from Member m where m.nickname=:nickname")
    Optional<Long> existNickname(@Param("nickname") String nickname);

    Optional<Member> findByLoginId(@Param("loginId") String loginId);

    @Query("select m.loginId from Member m where m.name=:name and m.tel=:tel")
    Optional<String> findByNameAndTel(@Param("name") String name, @Param("tel") String tel);
}
