package com.ssafy.discoverkorea.client.board.repository;

import com.ssafy.discoverkorea.client.board.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {
}
