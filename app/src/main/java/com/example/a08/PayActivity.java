package com.example.a08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PayActivity extends AppCompatActivity {
    List<FoodBean> sfood = new ArrayList<>();
    Sadapter sadapter;
    ListView lv3;
    TextView tvsum3,tvdtime;
    Button btnpay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        initView();
        sfood=initData();
        sadapter= new Sadapter(PayActivity.this,sfood);
        lv3.setAdapter(sadapter);
        initListener();
        Dtime();
    }
    private void Dtime() {
        new CountDownTimer(60000,1000){
            @Override
            public void onTick(long time) {
                tvdtime.setText(time/1000+"");
            }
            @Override
            public void onFinish() {
                finish();
            }
        }.start();
    }

    private void initListener() {
        btnpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PayActivity.this,DetailActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        lv3=findViewById(R.id.lv_3);
        tvsum3=findViewById(R.id.tv_sum3);
        tvdtime=findViewById(R.id.tv_dtime);
        btnpay=findViewById(R.id.btn_pay);
        tvsum3.setText(CartActivity.summ2+"");
    }

    private List<FoodBean> initData() {
        List<FoodBean> sfood = new ArrayList<>();
        for (FoodBean f : CartActivity.sfood){
            sfood.add(f);
        }
        return  sfood;
    }
}