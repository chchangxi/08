package com.example.a08;

import android.graphics.Bitmap;

public class FoodBean {
    String name;
    float price;
    int count;
    Bitmap bitmap;

    public FoodBean(String name, float price, int count, Bitmap bitmap) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.bitmap = bitmap;
    }

    public FoodBean() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
