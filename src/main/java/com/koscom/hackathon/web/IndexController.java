package com.koscom.hackathon.web;


import com.koscom.hackathon.config.auth.dto.SessionUser;
import com.koscom.hackathon.domain.user.User;
import com.koscom.hackathon.domain.user.UserRepository;
import com.koscom.hackathon.web.dto.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HttpSession httpSession;
    private final UserRepository userRepository;

    @GetMapping("/main")
    public String index() {
        return "login";
    }

    @GetMapping("/index")
    public String index2(){

        return "index";
    }

    @PostMapping("/index")
    public String index_userInfo(@RequestBody Object object){

        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        User user = userRepository.findByEmail(sessionUser.getEmail()).orElse(null);


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
        return "tables";
    }


}
