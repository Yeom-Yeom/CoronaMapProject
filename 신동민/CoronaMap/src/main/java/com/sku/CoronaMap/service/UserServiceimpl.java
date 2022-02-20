package com.sku.CoronaMap.service;

import com.sku.CoronaMap.domain.User;
import com.sku.CoronaMap.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class UserServiceimpl implements UserService{
    private final UserRepository userRepository;

    // 중복 아이디가 있나 = 아이디
    @Override
    public User JoinCheck(User user) {
        return userRepository.findByUserid (user.getUserid ());
    }

    // 가입이 되어 있나 = 아이디랑 패스워드
    @Override
    public User LoginCheck(User user, HttpSession session) {
        User member = userRepository.findByUseridAndPassword(user.getUserid (), user.getPassword ());
        if (member != null) { // 세션 변수 저장
            session.setAttribute("userid", member.getUserid());
            session.setAttribute("name", member.getName ());
        }
        return member;
    }

    // 가입
    @Override
    public User Join(User user) {
/*        System.out.println (user.getUserid ());
        System.out.println (user.getPassword ());
        System.out.println (user.getName ());
        System.out.println (user.getEmail ());*/
        User savedUser = userRepository.save (user);
        return savedUser;
    }

    @Override
    public void logout(HttpSession session) {
        session.invalidate(); // 세션 초기화
    }

    @Override
    public User findUser(HttpSession session) {
        return userRepository.findByUserid ((String) session.getAttribute ("userid"));
    }
}
