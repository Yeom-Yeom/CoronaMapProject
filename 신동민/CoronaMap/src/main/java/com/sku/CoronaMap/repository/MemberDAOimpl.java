package com.sku.CoronaMap.repository;

import com.sku.CoronaMap.domain.MemberDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

// 디비 연결
@Repository // 현재 클래스를 dao bean으로 등록
public class MemberDAOimpl implements MemberDAO {

    final SqlSession sqlSession; // SqlSession 의존관계 주입

    public MemberDAOimpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public String JoinCheck(MemberDTO dto) {
        return sqlSession.selectOne("member.JoinCheck", dto);
    }

    @Override
    public String LoginCheck(MemberDTO dto) {
        return sqlSession.selectOne("member.LoginCheck", dto);
    }

    @Override
    public void Join(MemberDTO dto) {
        sqlSession.selectOne("member.insert", dto);
    }
}
