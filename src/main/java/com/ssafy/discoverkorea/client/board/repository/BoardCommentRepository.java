package com.ssafy.discoverkorea.client.board.repository;

import com.ssafy.discoverkorea.client.board.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {
}
