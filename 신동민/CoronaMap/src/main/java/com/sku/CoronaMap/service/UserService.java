package com.sku.CoronaMap.service;

import com.sku.CoronaMap.domain.MemberDTO;
import com.sku.CoronaMap.domain.User;

import javax.servlet.http.HttpSession;

public interface UserService {
    public User JoinCheck(User user);
    public User LoginCheck(User user, HttpSession session);
    public User Join(User user);
    public void logout(HttpSession session);
    public User findUser(HttpSession session);
    //public void deleteById(String id); 탈퇴
}
