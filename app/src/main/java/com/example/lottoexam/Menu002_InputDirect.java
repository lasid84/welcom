package com.example.lottoexam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class Menu002_InputDirect extends AppCompatActivity {
    ArrayList<LottoNumber> data= new ArrayList<>();
    GridView listView;
    TextView textView;
    ArrayList<Integer> list = new ArrayList<Integer>();
    int listSize = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu002_inputdirect);

        Init();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String tagValue = parent.getItemAtPosition(position).toString();
                String LottoColor = ((TextView) view.findViewById(R.id.tv_lottoColor)).getText().toString();
                TextView tv_lottoNo = ((TextView) view.findViewById(R.id.tv_lottoNo));
                TextView tv_clickFlag = ((TextView) view.findViewById(R.id.tv_clickFlag));
                int lottoNo = Integer.parseInt(tv_lottoNo.getText().toString());

//                String NumberArray = textView.getText().toString();
                listSize = list.size();

                if (tv_clickFlag.getText() == "N") {

                    if (listSize == 6) {
                        Toast.makeText(Menu002_InputDirect.this, "6개까지만 선택 가능 합니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    tv_clickFlag.setText("Y");
                    list.add(lottoNo);

                    switch (Integer.parseInt(LottoColor))
                    {
                        case 0:
                            tv_lottoNo.setBackgroundResource(R.drawable.circle_yellow);
                            break;
                        case 1:
                            tv_lottoNo.setBackgroundResource(R.drawable.circle_skyblue);
                            break;
                        case 2:
                            tv_lottoNo.setBackgroundResource(R.drawable.circle_red);
                            break;
                        case 3:
                            tv_lottoNo.setBackgroundResource(R.drawable.circle_gray);
                            break;
                        case 4:
                            tv_lottoNo.setBackgroundResource(R.drawable.circle_green);
                            break;
                        default:
                            tv_lottoNo.setBackgroundResource(R.drawable.circle_green);
                            break;
                    }
                }
                else {
                    tv_clickFlag.setText("N");
                    int index = list.indexOf(lottoNo);
                    list.remove(index);
                    tv_lottoNo.setBackgroundResource(R.drawable.circle_normal);
                }

                String lottoNumbers = "";
                Collections.sort(list);

                for(int i = 0; i<list.size(); i++) {
                    if (i == 0)
                        lottoNumbers = list.get(i).toString();
                    else
                        lottoNumbers += "," + list.get(i).toString();
                }

                textView.setText(lottoNumbers);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void tv_initClick(View view) {
        Init();
    }

    private void Init() {
        data.clear();
        list.clear();
        for (int i = 1; i <= 45; i ++)
        {
            data.add(new LottoNumber(Integer.toString(i)));
        }
        LottoNumberAdapter adapter = new LottoNumberAdapter(data, "N");
        listView = findViewById(R.id.gv_LottoNumber);
        textView = findViewById(R.id.tv_textNumber);
        textView.setText("");

        listView.setAdapter(adapter);
    }
}
