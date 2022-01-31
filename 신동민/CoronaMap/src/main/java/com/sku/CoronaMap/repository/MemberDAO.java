package com.sku.CoronaMap.repository;

import com.sku.CoronaMap.domain.MemberDTO;

public interface MemberDAO {
    public String JoinCheck(MemberDTO dto);
    public String LoginCheck(MemberDTO dto);
    public void Join(MemberDTO dto);
}
