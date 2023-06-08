package com.ssafy.discoverkorea.client.board.service;

import com.ssafy.discoverkorea.client.board.Board;
import com.ssafy.discoverkorea.client.board.repository.BoardRepository;
import com.ssafy.discoverkorea.client.board.service.dto.AddBoardDto;
import com.ssafy.discoverkorea.client.member.Member;
import com.ssafy.discoverkorea.client.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.ssafy.discoverkorea.client.member.Gender.MALE;
import static com.ssafy.discoverkorea.common.entity.Active.ACTIVE;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("게시글 등록")
    void addBoard() {
        //given
        Member member = insertMember();
        AddBoardDto dto = AddBoardDto.builder()
                .title("board title")
                .content("board content")
                .build();

        //when
        Long boardId = boardService.addBoard(member.getLoginId(), dto);

        //then
        Optional<Board> findBoard = boardRepository.findById(boardId);
        assertThat(findBoard).isPresent();
    }

    private Member insertMember() {
        Member member = Member.builder()
                .loginId("ssafy")
                .loginPw("ssafy1234!")
                .name("김싸피")
                .tel("010-1234-1234")
                .email("ssafy@ssafy.com")
                .birth("1998")
                .gender(MALE)
                .nickname("동팔이")
                .active(ACTIVE)
                .build();
        return memberRepository.save(member);
    }
}