package com.ssafy.discoverkorea.client.board.repository;

import com.ssafy.discoverkorea.client.board.BoardScrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardScrapRepository extends JpaRepository<BoardScrap, Long> {

    @Query("select bs.id from BoardScrap bs join bs.member m where m.loginId=:loginId and bs.board.id=:boardId")
    Optional<Long> findByLoginIdAndBoardId(@Param("loginId") String loginId, @Param("boardId") Long boardId);
}
