package com.ssafy.discoverkorea.client.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.ssafy.discoverkorea.client.member.QMember.member;

@Repository
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public MemberQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Boolean existLoginId(String loginId) {
        Integer result = queryFactory
                .selectOne()
                .from(member)
                .where(member.loginId.eq(loginId))
                .fetchFirst();
        return result != null;
    }

    public Boolean existTel(String tel) {
        Integer result = queryFactory
                .selectOne()
                .from(member)
                .where(member.tel.eq(tel))
                .fetchFirst();
        return result != null;
    }

    public Boolean existEmail(String email) {
        Integer result = queryFactory
                .selectOne()
                .from(member)
                .where(member.email.eq(email))
                .fetchFirst();
        return result != null;
    }

    public Boolean existNickname(String nickname) {
        Integer result = queryFactory
                .selectOne()
                .from(member)
                .where(member.nickname.eq(nickname))
                .fetchFirst();
        return result != null;
    }
}
