package com.sku.CoronaMap.member;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


@Repository // 현재 클래스를 dao bean으로 등록
public class MemberDAOImpl implements MemberDAO {

    @Inject
    SqlSession sqlSession; // SqlSession 의존관계 주입

    @Override
    public String loginCheck(MemberDTO dto) {
        return sqlSession.selectOne("member.login_check", dto);
    }
}