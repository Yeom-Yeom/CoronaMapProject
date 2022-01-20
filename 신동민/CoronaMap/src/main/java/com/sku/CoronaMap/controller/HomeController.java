package com.sku.CoronaMap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model){
        return "corona/corona";
    }

    @GetMapping("/corona")
    public String corona(Model model){
        return "corona/corona";
    }
}
