package com.sku.CoronaMap.service;

import com.sku.CoronaMap.domain.MemberDTO;
import javax.servlet.http.HttpSession;

public interface MemberService {
    public String JoinCheck(MemberDTO dto);
    public String LoginCheck(MemberDTO dto, HttpSession session);
    public void Join(MemberDTO dto);
    public void logout(HttpSession session);
}
