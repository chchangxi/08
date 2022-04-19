package com.example.a08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    public static List<FoodBean> sfood = new ArrayList<>();
    FoodsAdapter sadpater;
    ListView lv2;
    TextView tvsum2;
    Button btngopay;
    public static float summ2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initView();
        sfood=initData();
        tvsum2.setText("￥"+OrderActivity.summ);
        sadpater= new FoodsAdapter(CartActivity.this, sfood, new Tese() {
            @Override
            public void change(float e) {
                tvsum2.setText("￥"+e+"");
                summ2=e;
            }
        });
        lv2.setAdapter(sadpater);
        initListener();
    }
    private void initListener() {
        btngopay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this,PayActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<FoodBean> initData() {
        List<FoodBean> food = new ArrayList<>();
        for (FoodBean f : OrderActivity.foods){
            if (f.getCount()!=0){
                food.add(f);
            }
        }
        return food;
    }

    private void initView() {
        lv2=findViewById(R.id.lv_2);
        tvsum2=findViewById(R.id.tv_sum2);
        btngopay=findViewById(R.id.btn_gopay);
    }
}