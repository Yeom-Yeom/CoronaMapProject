package com.sku.CoronaMap.repository;

import com.sku.CoronaMap.domain.Board;
import com.sku.CoronaMap.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByUser(User user);
}
