package com.sku.CoronaMap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/corona/*")
public class CoronaController {

    @GetMapping("statistics")
    public String statistics(Model model){
        return "corona/statistics";
    }

    @GetMapping("find")
    public String find(Model model){
        return "corona/find";
    }

    @GetMapping("board")
    public String board(Model model){
        return "corona/board";
    }
}
