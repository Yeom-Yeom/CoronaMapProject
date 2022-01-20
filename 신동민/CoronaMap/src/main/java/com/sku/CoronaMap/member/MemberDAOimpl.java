package com.sku.CoronaMap.member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import javax.inject.Inject;

// 디비 연결
@Repository // 현재 클래스를 dao bean으로 등록
public class MemberDAOimpl implements MemberDAO {

    final SqlSession sqlSession; // SqlSession 의존관계 주입

    public MemberDAOimpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public String loginCheck(MemberDTO dto) {
        return sqlSession.selectOne("member.login_check", dto);
    }
}
