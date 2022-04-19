package com.example.a08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    public  static List<FoodBean> foods=new ArrayList<>();
    FoodsAdapter adapters;
    ListView lv1;
    ViewFlipper vffood;
    TextView tvsum1;
    ImageView ivcart,ivleft,ivright;
    public static float summ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
        foods=initData();
        adapters = new FoodsAdapter(OrderActivity.this, foods, new Tese() {
            @Override
            public void change(float e) {
                tvsum1.setText("￥"+e+"");
                summ=e;
            }

        });
        lv1.setAdapter(adapters);
        initListener();
        getphoto();
    }

    private void getphoto() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = util_t.getDataByGet_t("http://10.0.2.2:8080/api/recommendgoods");
                    String data = jsonObject.getString("data");
                    JSONArray jsonArray = new JSONArray(data);
                    List<String> photoUrls = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        photoUrls.add(jsonArray.getString(i).replace("localhost", "10.0.2.2"));
                    }
                    vffood.setFlipInterval(2000);
                    vffood.startFlipping();
                    for (String s : photoUrls) {
                        Bitmap bitmap=util_t.urlToBitmap(s);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ImageView imageView = new ImageView(OrderActivity.this);
                                imageView.setImageBitmap(bitmap);
                                vffood.addView(imageView);
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initListener() {
        ivcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });
        ivright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vffood.stopFlipping();
                vffood.showNext();
                vffood.startFlipping();
            }
        });
        ivleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vffood.stopFlipping();
                vffood.showPrevious();
                vffood.startFlipping();
            }
        });
    }

    private List<FoodBean> initData() {

        try {
            JSONObject jsonObject =util_t.getDataByGet_t("http://10.0.2.2:8080/api/goods");
            String data=jsonObject.getString("data");
            JSONArray jsonArray = new JSONArray(data);
            List<FoodBean> foods = new ArrayList<>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject object=jsonArray.getJSONObject(i);
                FoodBean food = new FoodBean(object.getString("name"), (float) object.getDouble("price"),0,
                        util_t.urlToBitmap(object.getString("img_url").replace("localhost","10.0.2.2")));
                foods.add(food);
            }
            return foods;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    private void initView() {
        lv1=findViewById(R.id.lv_1);
        vffood=findViewById(R.id.vf_food);
        tvsum1=findViewById(R.id.tv_sum1);
        ivcart=findViewById(R.id.iv_cart);
        ivleft=findViewById(R.id.iv_left);
        ivright=findViewById(R.id.iv_right);
    }
}