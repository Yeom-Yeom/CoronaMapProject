package com.sku.CoronaMap.service;

import com.sku.CoronaMap.domain.Board;
import com.sku.CoronaMap.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceimpl implements BoardService {

    private final BoardRepository boardRepository;

    // 페이징 처리된 게시글 리스트 반환
    public Page<Board> findBoardList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber () <= 0 ? 0 : pageable.getPageNumber ()-1, pageable.getPageSize ());
        return boardRepository.findAll (pageable);
    }

    // 게시글 ID로 조회
    public Board findBoardById(Long id) {
        Board board = boardRepository.findById (id).orElse (new Board());
        return board;
    }

    // 게시글 저장
    public Board save(Board board){
        Board savedBoard = boardRepository.save (board);
        return savedBoard;
    }

    // 게시글 삭제
    public void deleteById(Long id){
        boardRepository.deleteById (id);
    }
}
