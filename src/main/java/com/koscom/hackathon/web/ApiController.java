package com.koscom.hackathon.web;


import com.koscom.hackathon.config.auth.dto.SessionUser;
import com.koscom.hackathon.domain.user.UserRepository;
import com.koscom.hackathon.web.dto.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class ApiController {

    private final HttpSession httpSession;

    @PostMapping("/userInfo")
    public String userInfo(@RequestBody UserInfo userInfo){

        System.out.println(userInfo);
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        sessionUser.setCurrentAsset(userInfo.getCurrentAsset());
        sessionUser.setAnnualInvest(userInfo.getAnnualInvest());
        sessionUser.setWorkingYear(userInfo.getWorkingYear());
        sessionUser.setRiskTolerance(userInfo.getRiskTolerance());
        System.out.println("session user: " + sessionUser);

        return "success";
    }


}
