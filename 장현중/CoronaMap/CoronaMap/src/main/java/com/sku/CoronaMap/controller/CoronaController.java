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
    public String coronastatistics(Model model){
        return "statistics";
    }
    @GetMapping("corona/find")
    public String coronafind(Model model){
        return "find";
    }
    @GetMapping("corona/board")
    public String coronaboard(Model model){
        return "board";
    }
}
