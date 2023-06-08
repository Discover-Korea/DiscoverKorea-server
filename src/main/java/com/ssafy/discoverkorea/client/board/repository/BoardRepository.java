package com.ssafy.discoverkorea.client.board.repository;

import com.ssafy.discoverkorea.client.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
