package com.example.a08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    List<FoodBean> efood = new ArrayList<>();
    Sadapter eadapter;
    TextView tvdesk,tvdate,tvtime,tvsum4;
    ListView lv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        efood=initData();
        eadapter= new Sadapter(DetailActivity.this,efood);
        lv4.setAdapter(eadapter);
        tvsum4.setText(CartActivity.summ2+"");
        initfile();

    }

    private void initfile() {
        SharedPreferences sp = getSharedPreferences("myspFile",0);
        String strfoods=sp.getString("payfoods","");
        try {
            JSONArray jsonArray = new JSONArray(strfoods);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject j = jsonArray.getJSONObject(i);
                FoodBean f = new FoodBean();
                f.setName(j.getString("name"));
                f.setPrice((float) j.getDouble("price"));
                f.setCount(j.getInt("count"));
                f.setBitmap(util_t.urlToBitmap(j.getString("bitmap").replace("localhost","10.0.2.2")));
                efood.add(f);
            }
            eadapter.refresh(efood);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private List<FoodBean> initData() {
        List<FoodBean> efood = new ArrayList<>();
        for (FoodBean f:CartActivity.sfood){
            efood.add(f);
        }
        return efood;
    }

    private void initView() {
        tvdate=findViewById(R.id.tv_date);
        tvdesk=findViewById(R.id.tv_dsek);
        tvtime=findViewById(R.id.tv_time);
        tvsum4=findViewById(R.id.tv_sum4);
        lv4=findViewById(R.id.lv_4);
        tvtime.setText(MainActivity.timee);
        tvdate.setText(MainActivity.datee);
        tvdesk.setText(MainActivity.deskk);

    }
}