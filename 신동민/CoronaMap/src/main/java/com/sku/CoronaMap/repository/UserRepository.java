package com.sku.CoronaMap.repository;

import com.sku.CoronaMap.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    User findByUserid(String userid);
    User findByUseridAndPassword(String userid, String password);
}
