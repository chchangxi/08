package com.example.a08;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Sadapter extends BaseAdapter {
    Context context;
    List<FoodBean> data;

    public Sadapter(Context context, List<FoodBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view==null){
            viewHolder = new ViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.list2,viewGroup,false);
            viewHolder.lv2food=view.findViewById(R.id.lv2_food);
            viewHolder.lv2count=view.findViewById(R.id.lv2_count);
            viewHolder.lv2name=view.findViewById(R.id.lv2_name);
            viewHolder.lv2price=view.findViewById(R.id.lv2_price);
            view.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) view.getTag();
        }
        FoodBean f =data.get(i);
        viewHolder.lv2price.setText(f.getPrice()+"");
        viewHolder.lv2name.setText(f.getName());
        viewHolder.lv2count.setText(f.getCount()+"");
        viewHolder.lv2food.setImageBitmap(f.getBitmap());
        return view;
    }

    public void refresh(List<FoodBean> efood) {
    }

    private final class ViewHolder{
        ImageView lv2food;
        TextView lv2name,lv2price,lv2count;

    }
}
