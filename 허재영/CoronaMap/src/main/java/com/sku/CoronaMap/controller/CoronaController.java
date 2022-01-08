package com.sku.CoronaMap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoronaController {
    @GetMapping("corona")
    public String corona(Model model){
        return "corona";
    }

    @GetMapping("corona/statistics")
    public String statics(Model model){
        return "statistics";
    } // 전체주석 : ctrl + /

    @GetMapping("corona/find")
    public String find(Model model){
        return "find";
    }
    @GetMapping("corona/board")
    public String board(Model model){
        return "board";
    }
}
