package com.sku.CoronaMap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@Controller // 컨트롤러 빈으로 등록
@RequestMapping("/member/*")
public class MemberController {

    private final MemberService memberService;

    // 의존관계 주입
    @Autowired
    public MemberController(MemberService memberService ){
        this.memberService = memberService;
    }

    @Inject
    // menu.do를 클릭하면 views/member/login.jsp로 이동
    @RequestMapping("login.do")
    public String login() {
        return "web/login";
    }

    @RequestMapping("login_check.do")
    public ModelAndView login_check(@ModelAttribute MemberDTO dto, HttpSession session) {
        String name = MemberService.logincheck(dto, session);
        ModelAndView mav = new ModelAndView();
        if (name != null) { // 로그인 성공 시
            mav.setViewName("home"); // 뷰의 이름
        } else { // 로그인 실패 시
            mav.setViewName("web/login");
            mav.addObject("message", "error");
        }
        return mav;
    }

    @RequestMapping("logout.do")
    public ModelAndView logout(HttpSession session, ModelAndView mav) {
        MemberService.logout(session);
        mav.setViewName("web/login");
        mav.addObject("message", "logout");
        return mav;
    }
}