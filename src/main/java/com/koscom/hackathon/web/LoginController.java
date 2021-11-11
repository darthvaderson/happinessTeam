package com.koscom.hackathon.web;


import com.koscom.hackathon.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class LoginController {

    private final HttpSession httpSession;


//    @GetMapping("/login")
//    public String loginPage(Model model) {
//
//        SessionUser user = (SessionUser)httpSession.getAttribute("user");
//        if(user != null){
//            model.addAttribute("user",user.getName());
//        }
//
//        return "login";
//    }
}
