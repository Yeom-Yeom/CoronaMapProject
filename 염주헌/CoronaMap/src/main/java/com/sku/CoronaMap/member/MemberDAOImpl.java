package com.sku.CoronaMap.member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class MemberDAOImpl implements MemberDAO {

    @Inject
    SqlSession sqlSession; // SqlSession 의존관계 주입

    @Override
    public String loginCheck(MemberDTO dto) {
        return sqlSession.selectOne("member.login_check", dto);
    }
}
