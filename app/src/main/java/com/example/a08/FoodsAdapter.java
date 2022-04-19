package com.example.a08;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FoodsAdapter extends BaseAdapter {
    Context context;
    List<FoodBean> data;
    Tese e;

    public FoodsAdapter(Context context, List<FoodBean> data, Tese e) {
        this.context = context;
        this.data = data;
        this.e = e;
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
            view= LayoutInflater.from(context).inflate(R.layout.list1,viewGroup,false);
            viewHolder.lv1add=view.findViewById(R.id.lv1_add);
            viewHolder.lv1food=view.findViewById(R.id.lv1_food);
            viewHolder.lv1count=view.findViewById(R.id.lv1_count);
            viewHolder.lv1min=view.findViewById(R.id.lv1_min);
            viewHolder.lv1name=view.findViewById(R.id.lv1_name);
            viewHolder.lv1price=view.findViewById(R.id.lv1_price);
            view.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) view.getTag();
        }
        FoodBean f =data.get(i);
        viewHolder.lv1price.setText(f.getPrice()+"");
        viewHolder.lv1name.setText(f.getName());
        viewHolder.lv1count.setText(f.getCount()+"");
        viewHolder.lv1food.setImageBitmap(f.getBitmap());
        viewHolder.lv1add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FoodBean f = (FoodBean) data.get(i);
                int c=f.getCount();
                c++;
                f.setCount(c);
                data.set(i,f);
                reFresh(data);
                e.change(sum(data));
            }
        });
        viewHolder.lv1min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FoodBean f = (FoodBean)  data.get(i);
                int c=f.getCount();
                c--;
                f.setCount(c);
                data.set(i,f);
                reFresh(data);
                e.change(sum(data));

            }
        });
        return view;
    }
    private void reFresh(List<FoodBean> data) {
        this.data= data;
        notifyDataSetChanged();
    }
    private float sum(List<FoodBean> data) {
        float sum=0;
        for (FoodBean f : data){
            sum+=f.getCount()*f.getPrice();
        }
        return  sum;
    }
    private final class ViewHolder{
        ImageView lv1food,lv1add,lv1min;
        TextView lv1name,lv1price,lv1count;

    }
}
