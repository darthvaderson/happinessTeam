package com.koscom.hackathon.config.auth.dto;


import com.koscom.hackathon.domain.user.User;
import lombok.Data;

import java.io.Serializable;

// Session에 담기 위해서는 직렬화를 해야 함.
// Session에는 너무 많은 데이터를 담으면 Out of Memory 일어날 수 있음.
@Data
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;
    private String currentAsset;
    private String annualInvest;
    private String workingYear;
    private String riskTolerance;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }

}
