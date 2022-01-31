package com.sku.CoronaMap.controller;

import com.sku.CoronaMap.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    final MemberService memberService;
    @Autowired
    public HomeController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String home(Model model, HttpSession session){
        memberService.logout(session);
        return "corona/corona";
    }

    @GetMapping("/corona")
    public ModelAndView corona(Model model, HttpSession session, ModelAndView mav){
        mav.setViewName("corona/corona"); // 뷰의 이름
        mav.addObject("login_message", session.getAttribute ("name"));
        return mav;
    }
}
