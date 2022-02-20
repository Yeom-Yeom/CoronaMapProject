package com.sku.CoronaMap.service;

import com.sku.CoronaMap.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    public Page<Board> findBoardList(Pageable pageable);
    public Board findBoardById(Long id);
    public Board save(Board board);
    public void deleteById(Long id);
}
