package com.example.lottoexam;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {
    SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy.MM.dd");
    Date ntime = new Date();
    String time1 = format1.format(ntime);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView Date_Textview = findViewById(R.id.txt_date);
        Date_Textview.setText(time1);

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



}
