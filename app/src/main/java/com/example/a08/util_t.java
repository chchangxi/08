package com.example.a08;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class util_t {
    public static JSONObject POSTJson(final String url, final String pera) throws Exception {
        final StringBuilder builder = new StringBuilder();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection connection = (HttpURLConnection) (new URL(url)).openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.getOutputStream().write(pera.getBytes("UTF-8"));
                    if (connection.getResponseCode() == 200) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String line;
                        while ((line = reader.readLine()) != null)
                            builder.append(line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.join();
        return new JSONObject(builder.toString());
    }
//    public static Response GET(String url) throws IOException{
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder().url(url).build();
//        Response response = client.newCall(request).execute();
//        return response;
//    }

    public static JSONObject Get(String url, String pera) throws Exception{
        final JSONObject[] jsonObject = {new JSONObject()};
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                OkHttpClient okHttpClient = new OkHttpClient();
//                Request request = new Request.Builder().url(url).build();
//                try {
//                    Response response=okHttpClient.newCall(request).execute();
//                    jsonObject[0] = new JSONObject(response.body().string());
//                    String code = jsonObject[0].getString("code");
//                    Log.i("TAG", "initData:------------------- "+code);
//                } catch (IOException | JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        return jsonObject[0];
    }




    public static JSONObject getDataByGet_t(String url) throws Exception{
        OkHttpClient client = new OkHttpClient();
        final String[] data={null};
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    Response response = client.newCall(request).execute();
                    data[0] = response.body().string();
                    Log.e("internet tag" + url, data[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.join();
        return new JSONObject(data[0]);
    }








    public static JSONObject geet(String url) {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    String data = response.body().string();
                    Log.e("internet tag" + url, data);
                    return new JSONObject(data);    //返回json对象

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

        return null;



    }
    public static JSONObject GETJson(final String url,final String pera) throws Exception {
        final StringBuilder builder=new StringBuilder();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL myurl = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) myurl.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.getOutputStream().write(pera.getBytes("UTF-8"));
                    if(connection.getResponseCode()==200){
                        InputStream in = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            builder.append(line);
                        }
                        connection.disconnect();
                        reader.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.join();
        return new JSONObject(builder.toString());
    }


    public static Bitmap urlToBitmap(final String path) throws Exception{
        final Bitmap[] bitmap = {null};
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    HttpURLConnection connection = (HttpURLConnection)(new URL(path.replace("127.0.0.1", "10.0.2.2")))
                            .openConnection();
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    if(connection.getResponseCode() == 200){
                        bitmap[0] = BitmapFactory.decodeStream(connection.getInputStream());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.join();
        return bitmap[0];
    }
}
