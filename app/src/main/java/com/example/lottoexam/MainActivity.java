package com.example.lottoexam;

import android.annotation.TargetApi;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import androidx.annotation.RequiresApi;


@TargetApi(Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {
    SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy.MM.dd");

    Date ntime = new Date();
    String time1 = format1.format(ntime);
    ArrayList<LottoNumber> Winning_data= new ArrayList<>();
    GridView list_winNo;

    JsonObject jsonObject;
    RequestQueue requestQueue;

    TextView Round_Textview;
    TextView tv_test;
    int _round;
    TextView Date_Textview;


    public static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Date_Textview = findViewById(R.id.txt_date);
        Round_Textview = findViewById(R.id.txt_round);
        tv_test = findViewById(R.id.tv_test);

        if(requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        db = Room.databaseBuilder(this, AppDatabase.class, "lottowin-db")
                .allowMainThreadQueries()
                .build();

        try {
            SetRound();
        } catch (ParseException e) {
            e.printStackTrace();
        }        //
        //LottoWinNumber();

        UpdateLottoWinDb();
        requestLotto();




        ArrayList<ItemList> data = new ArrayList<>();
        data.add(new ItemList("1", "QR코드입력"));
        data.add(new ItemList("2", "구입번호 직접입력"));
        data.add(new ItemList("3", "구입번호목록&당첨확인"));

        ItemListAdapter adapter = new ItemListAdapter(data);

        GridView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, position + "번째 아이템 선택", Toast.LENGTH_SHORT).show();
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(MainActivity.this, menu01_ScanActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, Menu002_InputDirect.class);
                        startActivity(intent);
                        break;

                }

            }
        });

    }

    private void UpdateLottoWinDb() {

        int cnt = db.Dao_LottoWinNumber().getAllcount();
        int _st_round;
        if (cnt == 0) {
            _st_round = 1;
        } else {
            _st_round = db.Dao_LottoWinNumber().getMaxRound() + 1;
        }

        for (int i = _st_round; i <= _round; i++) {
            UpdateWinLotto(i);
        }
    }


    public void requestLotto() {

//        String url = "https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=" +  String.valueOf(_round);
//        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                jsonObject = (JsonObject) JsonParser.parseString(response);
//                String no1 = jsonObject.get("drwtNo1").toString();
//                String no2 = jsonObject.get("drwtNo2").toString();
//                String no3 = jsonObject.get("drwtNo3").toString();
//                String no4 = jsonObject.get("drwtNo4").toString();
//                String no5 = jsonObject.get("drwtNo5").toString();
//                String no6 = jsonObject.get("drwtNo6").toString();
//                String bonus = jsonObject.get("bnusNo").toString();
//                Winning_data.clear();
//                Winning_data.add(new LottoNumber(no1));
//                Winning_data.add(new LottoNumber(no2));
//                Winning_data.add(new LottoNumber(no3));
//                Winning_data.add(new LottoNumber(no4));
//                Winning_data.add(new LottoNumber(no5));
//                Winning_data.add(new LottoNumber(no6));
//                Winning_data.add(new LottoNumber("+"));
//                Winning_data.add(new LottoNumber(bonus));
//
//                LottoNumberAdapter adapter = new LottoNumberAdapter(Winning_data, "Y");
//                list_winNo = findViewById(R.id.list_winNo);
//                list_winNo.setAdapter(adapter);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                return params;
//            }
//        };
//        request.setShouldCache(false);
//        requestQueue.add(request);

        String jsonstring = db.Dao_LottoWinNumber().getWinNumber(String.valueOf(_round)).toString();
        jsonstring = jsonstring.replace("[", "");
        jsonstring = jsonstring.replace("]", "");

        jsonObject = (JsonObject) JsonParser.parseString(jsonstring);

        String result = jsonObject.get("result").toString().replace("\"", "");

        if (result.equals("fail")) {
            jsonObject = (JsonObject) JsonParser.parseString(String.valueOf(_round - 1));
        }

        String date = jsonObject.get("date").toString().replace("\"", "");

        Round_Textview.setText(jsonObject.get("round").toString());
        Date_Textview.setText(date);

        String no1 = jsonObject.get("no1").toString();
        String no2 = jsonObject.get("no2").toString();
        String no3 = jsonObject.get("no3").toString();
        String no4 = jsonObject.get("no4").toString();
        String no5 = jsonObject.get("no5").toString();
        String no6 = jsonObject.get("no6").toString();
        String bonus = jsonObject.get("bonus").toString();

        Winning_data.clear();
        Winning_data.add(new LottoNumber(no1));
        Winning_data.add(new LottoNumber(no2));
        Winning_data.add(new LottoNumber(no3));
        Winning_data.add(new LottoNumber(no4));
        Winning_data.add(new LottoNumber(no5));
        Winning_data.add(new LottoNumber(no6));
        Winning_data.add(new LottoNumber("+"));
        Winning_data.add(new LottoNumber(bonus));

        LottoNumberAdapter adapter = new LottoNumberAdapter(Winning_data, "Y");
        list_winNo = findViewById(R.id.list_winNo);
        list_winNo.setAdapter(adapter);

    }

    private void SetRound () throws ParseException {
        String lotto_start = "20021207 2058";

        SimpleDateFormat dateSet = new SimpleDateFormat("yyyyMMdd HHmm");
        String date = dateSet.format(new Date());
        Date Now_Date = dateSet.parse(date);
        Date Lotto_StartDate = dateSet.parse(lotto_start);
        long diff = Now_Date.getTime() - Lotto_StartDate.getTime() ;

        long calDateDays = diff / ( 24*60*60*1000) /7;
        _round = (int)calDateDays + 1;
        Round_Textview.setText(String.valueOf(_round));
    }

    public void UpdateWinLotto(int _now_round) {
        try {
            final String mround = String.valueOf(_now_round);
            String url = "http://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo=" +  mround;
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    jsonObject = (JsonObject) JsonParser.parseString(response);
                    String returnvalue = jsonObject.get("returnValue").toString();
                    if (returnvalue == "fail")
                        return;

                    String date = jsonObject.get("drwNoDate").toString();
                    String no1 = jsonObject.get("drwtNo1").toString();
                    String no2 = jsonObject.get("drwtNo2").toString();
                    String no3 = jsonObject.get("drwtNo3").toString();
                    String no4 = jsonObject.get("drwtNo4").toString();
                    String no5 = jsonObject.get("drwtNo5").toString();
                    String no6 = jsonObject.get("drwtNo6").toString();
                    String bonus = jsonObject.get("bnusNo").toString();

                    String totSellamnt = jsonObject.get("totSellamnt").toString();
                    String firstAccumamnt = jsonObject.get("firstAccumamnt").toString();
                    String firstPrzwnerCo = jsonObject.get("firstPrzwnerCo").toString();
                    String firstWinamnt = jsonObject.get("firstWinamnt").toString();

                    db.Dao_LottoWinNumber().insert(new Table_LottoWinNumber(mround, date, no1, no2, no3, no4, no5, no6, bonus
                                                                          , totSellamnt, firstAccumamnt, firstPrzwnerCo, firstWinamnt, returnvalue));


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    NetworkResponse response = error.networkResponse;
                    String errorMsg = "";
                    if(response != null && response.data != null){
                        String errorString = new String(response.data);
                        Log.i("log error", errorString);}
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("key_1","value_1");
                    params.put("key_2", "value_2");
                    Log.i("sending ", params.toString());

                    return params;
                }
            };
            //request.setShouldCache(false);
            request.setRetryPolicy(new DefaultRetryPolicy(10000, 1, 1.0f));
            requestQueue.add(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
