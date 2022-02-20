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

    // 중복 아이디가 있나
    @Override
    public String JoinCheck(MemberDTO memberDTO) {
        return sqlSession.selectOne("member.JoinCheck", memberDTO);
    }

    // 가입이 되어 있나
    @Override
    public String LoginCheck(MemberDTO memberDTO) {
        return sqlSession.selectOne("member.LoginCheck", memberDTO);
    }

    // 가입
    @Override
    public void Join(MemberDTO memberDTO) {
        sqlSession.selectOne("member.insert", memberDTO);
    }
}
