package com.ssafy.discoverkorea.client.board.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.discoverkorea.client.api.response.board.BoardDetailResponse;
import com.ssafy.discoverkorea.client.api.response.board.BoardResponse;
import com.ssafy.discoverkorea.client.board.repository.dto.SearchBoardCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.ssafy.discoverkorea.client.board.QBoard.board;
import static com.ssafy.discoverkorea.client.member.QMember.member;
import static com.ssafy.discoverkorea.common.entity.Active.ACTIVE;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class BoardQueryRepository {

    private final JPAQueryFactory queryFactory;

    public BoardQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<BoardResponse> searchByCondition(SearchBoardCondition condition, Pageable pageable) {
        List<Long> ids = queryFactory
                .select(board.id)
                .from(board)
                .where(
                        board.active.eq(ACTIVE),
                        isKeyword(condition.getKeyword())
                )
                .orderBy(board.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        return queryFactory
                .select(Projections.constructor(BoardResponse.class,
                        board.title,
                        board.content,
                        board.hitCount,
                        board.likeCount,
                        board.scrapCount,
                        board.commentCount,
                        board.createdDate,
                        board.member.nickname
                ))
                .from(board)
                .join(board.member, member)
                .where(board.id.in(ids))
                .orderBy(board.createdDate.desc())
                .fetch();
    }

    public long totalCountByCondition(SearchBoardCondition condition) {
        return queryFactory
                .select(board.id)
                .from(board)
                .where(
                        board.active.eq(ACTIVE),
                        isKeyword(condition.getKeyword())
                )
                .fetch()
                .size();
    }

    public BoardDetailResponse getBoard(Long boardId) {
        return queryFactory
                .select(Projections.constructor(BoardDetailResponse.class,
                        Expressions.asNumber(boardId),
                        board.title,
                        board.content,
                        board.hitCount,
                        board.likeCount,
                        board.scrapCount,
                        board.commentCount,
                        board.createdDate,
                        board.member.nickname))
                .from(board)
                .join(board.member, member)
                .where(board.id.eq(boardId))
                .fetchOne();
    }

    private BooleanExpression isKeyword(String keyword) {
        String likeCond = "%" + keyword + "%";
        return hasText(keyword) ? board.title.like(likeCond).or(board.content.eq(likeCond)) : null;
    }
}
