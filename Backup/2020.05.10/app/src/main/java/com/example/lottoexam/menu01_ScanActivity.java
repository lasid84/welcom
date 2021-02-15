package com.example.lottoexam;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import static com.example.lottoexam.MainActivity.db;

public class menu01_ScanActivity extends AppCompatActivity {

    private Button buttonScan;
    private TextView textViewName, textViewAddress, textViewResult;

    //qr code scanner object
    private IntentIntegrator qrScan;
    int _round = 0;
    JsonObject jsonObject;
    RequestQueue requestQueue;
    TextView Round_Textview;
    TextView Date_Textview;
    ArrayList<LottoNumber> Winning_data= new ArrayList<>();
    GridView list_winNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu001_scan);

        buttonScan = (Button) findViewById(R.id.buttonScan);

        Date_Textview = findViewById(R.id.txt_date);
        Round_Textview = findViewById(R.id.txt_round);

        //intializing scan object
//        qrScan = new IntentIntegrator(this);
//        qrScan.setPrompt("Scanning...");
//        qrScan.setOrientationLocked(false);
//        qrScan.initiateScan();

        qrScan = new IntentIntegrator(this);
        qrScan.setOrientationLocked(false); // default가 세로모드인데 휴대폰 방향에 따라 가로, 세로로 자동 변경됩니다.
        qrScan.setPrompt("");
        qrScan.initiateScan();

        //button onClick
        buttonScan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                qrScan.initiateScan();
            }
        });
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //qrcode 가 없으면
            if (result.getContents() == null) {
                Toast.makeText(menu01_ScanActivity.this, "취소!", Toast.LENGTH_SHORT).show();

            } else {
                //qrcode 결과가 있으면
                Toast.makeText(menu01_ScanActivity.this, "스캔완료!", Toast.LENGTH_SHORT).show();
                try {
                    //data를 json으로 변환
//                    JSONObject obj = new JSONObject(result.getContents());
//                    textViewName.setText(obj.getString("name"));
//                    textViewAddress.setText(obj.getString("address"));

                    //http://m.dhlottery.co.kr/?v=0910q070818333443q032025262734q192027293545q161920252829q0911283637421348754762
                    String _str = result.getContents();
                    _str = _str.replace("http://m.dhlottery.co.kr/?v=", "");
                    _round = Integer.parseInt(_str.substring(1,4));
                    _str = _str.substring(5);

                    String _temp_auto[] = _str.split("q", 10);
                    String _temp_manual[] = _str.split("m", 10);
                    int _cnt = _temp_auto.length;
                    _cnt = _cnt + _temp_manual.length;




//                } catch (JSONException e) {
                } catch (Exception e) {
                    e.printStackTrace();
                    //Toast.makeText(MainActivity.this, result.getContents(), Toast.LENGTH_LONG).show();
                    textViewResult.setText(result.getContents());
                }
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void requestLotto() {

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

    @RequiresApi(api = Build.VERSION_CODES.N)
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

}

