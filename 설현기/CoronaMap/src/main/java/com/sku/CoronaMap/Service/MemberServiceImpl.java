package com.sku.CoronaMap.Service;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import com.sku.CoronaMap.member.MemberDAO;
import com.sku.CoronaMap.member.MemberDTO;
import org.springframework.stereotype.Service;


@Service // service bean으로 등록
public class MemberServiceImpl implements MemberService {

    @Inject
    MemberDAO memberDao;
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