package com.example.lottoexam;

import android.icu.text.SimpleDateFormat;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class LottoWinningResult {
    int _round = -1;
    JsonObject jsonObject;
    public static RequestQueue requestQueue;
    ArrayList<String> WinnigNoList = new ArrayList<String>();
    String bonusNo;

    public ArrayList<String> getWinnigNoList() {
        return WinnigNoList;
    }

    public String getBonusNo() {
        return bonusNo;
    }

    public LottoWinningResult(int _round) {
        this._round = _round;
    }

    public void requestLotto() {
        if(_round == -1) {
            //Toast.makeText(GetWinningLotto.this , "회차 번호를 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=" + _round;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override public void onResponse(String response) {
                jsonObject = (JsonObject) JsonParser.parseString(response);
                WinnigNoList.clear();
                WinnigNoList.add(jsonObject.get("drwtNo1").toString());
                WinnigNoList.add(jsonObject.get("drwtNo2").toString());
                WinnigNoList.add(jsonObject.get("drwtNo3").toString());
                WinnigNoList.add(jsonObject.get("drwtNo4").toString());
                WinnigNoList.add(jsonObject.get("drwtNo5").toString());
                WinnigNoList.add(jsonObject.get("drwtNo6").toString());
                bonusNo = jsonObject.get("bnusNo").toString();
            }
        }, new Response.ErrorListener() {
            @Override public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>(); return params;
            }
        }; request.setShouldCache(false); requestQueue.add(request);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void GetRoundByDate (Date ndate) throws ParseException {
        String lotto_start = "20021207 2058";
        SimpleDateFormat formatter = new SimpleDateFormat ( "yyyyMMdd HHmm", Locale.KOREA );

        SimpleDateFormat dateSet = new SimpleDateFormat("yyyyMMdd HHmm");
        String date = dateSet.format(new Date());
        Date startDate = dateSet.parse(date);
        Date endDate = dateSet.parse("20021207 2058");
        long diff = startDate.getTime() - endDate.getTime() ;

        try {
            Date FirstDate = formatter.parse(lotto_start);
            long calDateDays = diff / ( 24*60*60*1000) /7;
            _round = (int)calDateDays;
        }
        catch (ParseException e) {

        }


    }


}
