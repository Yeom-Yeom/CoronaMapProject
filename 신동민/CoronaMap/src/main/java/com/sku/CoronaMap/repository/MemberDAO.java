package com.sku.CoronaMap.repository;

import com.sku.CoronaMap.domain.MemberDTO;

public interface MemberDAO {
    public String loginCheck(MemberDTO dto);
}
