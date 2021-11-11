package com.koscom.hackathon.web;


import com.koscom.hackathon.domain.user.api.WebAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller

public class IndexController {

    private final WebAPI webAPI;

    @GetMapping("/main")
    public String index() {
        return "login";
    }

    @GetMapping("/index")
    public String index2(Model model){

        System.out.println("SP500 MA value:"+webAPI.getSP500_MA());
        model.addAttribute("SP500_MA",webAPI.getSP500_MA());
        model.addAttribute("SP500_PRE",webAPI.getSP500_PRE());
        model.addAttribute("UNRATE_MA",webAPI.getUNRATE_MA());
        model.addAttribute("UNRATE_PRE",webAPI.getUNRATE_PRE());
        return "index";
    }

    @GetMapping("/survey")
    public String survey() {
        return "survey";
    }

    @GetMapping("/charts")
    public String chart(){
        return "charts";
    }

    @GetMapping("/tables")
    public String table(){
        return "tables" ;
    }
}
