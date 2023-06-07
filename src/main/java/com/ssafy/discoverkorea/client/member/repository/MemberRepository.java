package com.ssafy.discoverkorea.client.member.repository;

import com.ssafy.discoverkorea.client.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
