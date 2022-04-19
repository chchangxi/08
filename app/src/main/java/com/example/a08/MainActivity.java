package com.example.a08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    List<String> date=new ArrayList<>();
    Spinner spdate,sptime,sptable;
    Button btnsub,btnn;
    public static String datee,deskk,timee,paysum;
    String[] time={"请选择时间段","10:00-12:00","12:00-14:00","14:00-16:00","16:00-18:00","20:00-22:00","22:00-24:00"};
    String[] table={"请选择桌型","小桌(1-2人)","中桌(2-4人)","大桌(4-8人)","单间(8人以上)"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getTime();
        initView();
        initData();
        initListener();

    }

    private void initListener() {
        Intent intent = new Intent(this, OrderActivity.class);
        btnsub.setOnClickListener(v -> {
            startActivity(intent);
        });
        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject=util_t.geet("http://10.0.2.2:8080/api/goods");
                        Log.d("MainActivity", "run: "+jsonObject);
                    }
                }).start();
            }
        });
    }

    private void getTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        date.add("请选择日期");
        for (int i=0;i<7;i++){
            calendar.set(Calendar.DAY_OF_YEAR,Calendar.DAY_OF_MONTH+1);
            date.add(format.format(calendar.getTime()));
        }
    }

    private void initData() {
        sptime.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,time));
        sptable.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,table));
        spdate.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,date));
    }

    private void initView() {
        spdate=findViewById(R.id.sp_date);
        sptime=findViewById(R.id.sp_time);
        sptable=findViewById(R.id.sp_table);
        btnsub=findViewById(R.id.btn_submit);
        btnn=findViewById(R.id.btn_cancel);
    }
}