package com.ssafy.discoverkorea.client.board.repository;

import com.ssafy.discoverkorea.client.board.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

    @Query("select bl.id from BoardLike bl join bl.member m where m.loginId=:loginId and bl.board.id=:boardId")
    Optional<Long> findByLoginIdAndBoardId(@Param("loginId") String loginId, @Param("boardId") Long boardId);
}
