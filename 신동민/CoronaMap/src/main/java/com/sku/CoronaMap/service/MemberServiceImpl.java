package com.sku.CoronaMap.service;

import com.sku.CoronaMap.repository.MemberDAO;
import com.sku.CoronaMap.domain.MemberDTO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service // service bean으로 등록
public class MemberServiceImpl implements MemberService {

    final MemberDAO memberDao;

    public MemberServiceImpl(MemberDAO memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public String loginCheck(MemberDTO dto, HttpSession session) {
        String name = memberDao.loginCheck(dto);
        if (name != null) { // 세션 변수 저장
            session.setAttribute("userid", dto.getUserid());
            session.setAttribute("name", name);
        }
        return name;
    }

    @Override
    public void logout(HttpSession session) {
        session.invalidate(); // 세션 초기화
    }
}
