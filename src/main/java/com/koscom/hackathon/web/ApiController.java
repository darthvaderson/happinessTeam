package com.koscom.hackathon.web;


import com.koscom.hackathon.config.auth.dto.SessionUser;
import com.koscom.hackathon.domain.user.User;
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
    private final UserRepository userRepository;

    @PostMapping("/userInfo")
    public String userInfo(@RequestBody UserInfo userInfo){

        System.out.println(userInfo);
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        User user = userRepository.findByEmail(sessionUser.getEmail()).orElse(null);
        int temp = Integer.parseInt(userInfo.getAnnualInvest()) * 12;

        user.updateAssetInfo(userInfo.getCurrentAsset(),String.valueOf(temp), userInfo.getWorkingYear(), userInfo.getRiskTolerance());

        System.out.println("session user: " + sessionUser);

        return "success";
    }


}
