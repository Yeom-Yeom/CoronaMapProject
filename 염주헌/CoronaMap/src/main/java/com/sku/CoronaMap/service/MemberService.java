package com.sku.CoronaMap.service;

import com.sku.CoronaMap.member.MemberDTO;
import javax.servlet.http.HttpSession;

public interface MemberService {
    public String loginCheck(MemberDTO dto, HttpSession session);
    public void logout(HttpSession session);
}
