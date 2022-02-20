package com.sku.CoronaMap.controller;

import com.sku.CoronaMap.domain.MemberDTO;
import com.sku.CoronaMap.domain.User;
import com.sku.CoronaMap.service.MemberService;
import com.sku.CoronaMap.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
public class MemberController {

    final UserService userService;
    private String Join_message = "";
    private String login_message = "";
/*    // 의존관계 주입
    @Inject
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }*/

    @RequestMapping("Join")
    public ModelAndView Join(ModelAndView mav) {
        mav.setViewName("member/Join");
        mav.addObject("Join_message", Join_message);
        return mav;
    }

    @RequestMapping("Join_check")
    public ModelAndView Join_check(@ModelAttribute User user, ModelAndView mav) {
        //String id = memberService.JoinCheck(dto);
        User id = userService.JoinCheck(user);

        if (id != null) { // 가입 실패 시
            mav.setViewName("redirect:Join"); // 뷰의 이름
            Join_message = "fail";
        } else { // 가입 성공 시
            userService.Join (user);//인서트
            mav.setViewName("redirect:login");
            Join_message = "success";
        }
        login_message = "";

        return mav;
    }

    @GetMapping("login")
    public ModelAndView login(ModelAndView mav) {
        mav.setViewName("member/login");
        mav.addObject("Join_message", Join_message);
        mav.addObject("login_message", login_message);
        return mav;
    }

    @RequestMapping("login_check")
    public ModelAndView login_check(@ModelAttribute User user, HttpSession session, ModelAndView mav) {
        //String name = memberService.LoginCheck(dto, session);
        User name = userService.LoginCheck(user, session);

        if (name != null) { // 로그인 성공 시
            mav.setViewName("redirect:/corona"); // 뷰의 이름
            Join_message = "";
            login_message = "";
        } else { // 로그인 실패 시
            mav.setViewName("redirect:login");
            Join_message = "";
            login_message = "error";
        }
        return mav;
    }

    @RequestMapping("logout")
    public ModelAndView logout(ModelAndView mav) {
        Join_message = "";
        login_message = "";
        mav.setViewName("redirect:/");
        //mav.addObject("logout_message", null);
        return mav;
    }
}
