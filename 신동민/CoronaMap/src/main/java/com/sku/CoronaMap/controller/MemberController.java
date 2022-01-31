package com.sku.CoronaMap.controller;

import com.sku.CoronaMap.domain.MemberDTO;
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
    String name;
    String id;
    // 의존관계 주입
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    // @Inject
    @RequestMapping("Join")
    public String Join() {
        return "member/Join";
    }

    @RequestMapping("Join_check")
    public ModelAndView Join_check(@ModelAttribute MemberDTO dto, ModelAndView mav) {
        id = memberService.JoinCheck(dto);

        if (id != null) { // 가입 실패 시
            mav.setViewName("member/Join"); // 뷰의 이름
            mav.addObject("Join_message", "fail");
        } else { // 가입 성공 시
            memberService.Join (dto);//인서트
            mav.setViewName("member/login");
            mav.addObject("Join_message", "success");
        }
        return mav;
    }

    @RequestMapping("login")
    public String login() {
        return "member/login";
    }

    @RequestMapping("login_check")
    public ModelAndView login_check(@ModelAttribute MemberDTO dto, HttpSession session, ModelAndView mav) {
        name = memberService.LoginCheck(dto, session);
        //mav = new ModelAndView ();
        if (name != null) { // 로그인 성공 시
            mav.setViewName("corona/corona"); // 뷰의 이름
            mav.addObject("login_message", session.getAttribute ("name"));
        } else { // 로그인 실패 시
            mav.setViewName("member/login");
            mav.addObject("login_message", "error");
        }
        return mav;
    }

    @RequestMapping("logout")
    public ModelAndView logout(HttpSession session, ModelAndView mav) {
        memberService.logout(session);

        mav.setViewName("corona/corona");
        mav.addObject("logout_message", null);
        return mav;
    }
}
