package com.sku.CoronaMap.controller;

import com.sku.CoronaMap.member.MemberDTO;
import com.sku.CoronaMap.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/member/*")
public class MemberController {

    final MemberService memberService;

    // 의존관계 주입
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @Inject
    // menu.do를 클릭하면 views/member/login.jsp로 이동
    @RequestMapping("login.do")
    public String login() {
        return "member/login";
    }

    @RequestMapping("login_check.do")
    public ModelAndView login_check(@ModelAttribute MemberDTO dto, HttpSession session) {
        String name = memberService.loginCheck(dto, session);
        ModelAndView mav = new ModelAndView();
        if (name != null) { // 로그인 성공 시
            mav.setViewName("corona/corona"); // 뷰의 이름
        } else { // 로그인 실패 시
            mav.setViewName("member/login");
            mav.addObject("message", "error");
        }
        return mav;
    }

    @RequestMapping("logout.do")
    public ModelAndView logout(HttpSession session, ModelAndView mav) {
        memberService.logout(session);
        mav.setViewName("member/login");
        mav.addObject("message", "logout");
        return mav;
    }
    // view 매핑
/*    @RequestMapping("login")
    public String login(Model model){
        return "mappers/login";
    }*/
}
