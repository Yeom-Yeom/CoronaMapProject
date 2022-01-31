package com.sku.CoronaMap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/corona/*")
public class CoronaController {

    @GetMapping("statistics")
    public ModelAndView statistics(Model model, HttpSession session, ModelAndView mav){
        mav.setViewName("corona/statistics"); // 뷰의 이름
        mav.addObject("login_message", session.getAttribute ("name"));
        return mav;
    }

    @GetMapping("find")
    public ModelAndView find(Model model, HttpSession session, ModelAndView mav){
        mav.setViewName("corona/find"); // 뷰의 이름
        mav.addObject("login_message", session.getAttribute ("name"));
        return mav;
    }

    @GetMapping("board")
    public ModelAndView board(Model model, HttpSession session, ModelAndView mav){
        mav.setViewName("corona/board"); // 뷰의 이름
        mav.addObject("login_message", session.getAttribute ("name"));
        return mav;
    }
}
