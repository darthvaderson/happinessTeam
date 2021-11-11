package com.koscom.hackathon.domain.user.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.koscom.hackathon.web.APIController;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Getter
@NoArgsConstructor
@Component
public class WebAPI {
    private double SP500_MA;
    private double UNRATE_MA;
    private double SP500_PRE;
    private double UNRATE_PRE;

    @PostConstruct
    public void init(){
        APIController apiController=new APIController();
        String tmpAddr="https://api.stlouisfed.org/fred/series/observations?series_id=UNRATE&api_key=615c274fe3032b0014b9ce7bda6c0751&file_type=json";

        String tmpBody= "";
        tmpBody= apiController.get(tmpAddr);

        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject)jsonParser.parse(tmpBody);
        JsonArray jsonarr = jo.getAsJsonArray("observations");

        double unEmploymentRate_Sum = 0.0;
        double unEmploymentRate_MA12 = 0.0;

        double prevMonth_unEmploymentRate = 0.0;
        for(int i=jsonarr.size()-1; i>jsonarr.size()-13;i--) {

            JsonObject object = (JsonObject) jsonarr.get(i);
            //System.out.println("날짜:" + object.get("date"));
            //System.out.println("값:" + object.get("value"));
            unEmploymentRate_Sum += object.get("value").getAsDouble();
            if(i==jsonarr.size()-1) prevMonth_unEmploymentRate = object.get("value").getAsDouble();
        }
        unEmploymentRate_MA12 = unEmploymentRate_Sum/12.0;
        unEmploymentRate_MA12 = Math.round(unEmploymentRate_MA12*10)/10.0;
        //unEmploymentRate_MA12 -> 12개월 실업률 이동평균
        //prevMonth_unEmploymentRate -> 전월 실업률

        System.out.println("12개월 실업률 이동평균: " +unEmploymentRate_MA12);
        System.out.println("전월 실업률: " +prevMonth_unEmploymentRate);

        String tmpAddr2="https://api.stlouisfed.org/fred/series/observations?series_id=SP500&api_key=615c274fe3032b0014b9ce7bda6c0751&file_type=json";
        String tmpBody2= "";
        tmpBody2= apiController.get(tmpAddr2);

        JsonParser jsonParser2 = new JsonParser();
        JsonObject jo2 = (JsonObject)jsonParser2.parse(tmpBody2);
        JsonArray jsonarr2 = jo2.getAsJsonArray("observations");

        double SP500_Sum = 0.0;
        double SP500_MA200 = 0.0;
        double prevMonth_SP500 = 0.0;
        double idx= 0.0;
        for(int i=jsonarr2.size()-1; i>jsonarr2.size()-201;i--) {

            JsonObject object = (JsonObject) jsonarr2.get(i);
            String tmp = object.get("value").getAsString();
            if(tmp.equals(".")) continue;
            idx+=1.0;
            SP500_Sum += object.get("value").getAsDouble();
            if(i==jsonarr2.size()-1) prevMonth_SP500 = object.get("value").getAsDouble();
        }

        System.out.println(idx);
        SP500_MA200 = SP500_Sum/idx;
        SP500_MA200 = Math.round(SP500_MA200*100)/100.0;

        this.SP500_MA = SP500_MA200;
        this.UNRATE_MA = unEmploymentRate_MA12;
        this.SP500_PRE = prevMonth_SP500;
        this.UNRATE_PRE = prevMonth_unEmploymentRate;
    }

    public WebAPI(double SP500_MA, double UNRATE_MA, double SP500_PRE, double UNRATE_PRE) {
        this.SP500_MA = SP500_MA;
        this.UNRATE_MA = UNRATE_MA;
        this.SP500_PRE = SP500_PRE;
        this.UNRATE_PRE = UNRATE_PRE;
    }

    public double getSP500_PRE() {
        return SP500_PRE;
    }

    public double getUNRATE_PRE() {
        return UNRATE_PRE;
    }

    public double getSP500_MA() {
        return SP500_MA;
    }

    public double getUNRATE_MA() {
        return UNRATE_MA;
    }
}
