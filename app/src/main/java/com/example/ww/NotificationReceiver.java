package com.example.ww;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotificationReceiver extends BroadcastReceiver {
    String INTENT_ACTION = Intent.ACTION_BOOT_COMPLETED;

    private GpsTracker gpsTracker;
    double lat;
    double lon;
    String cw, cs;

    @Override
    public void onReceive(final Context context, Intent intent) {
        Log.d("알림 받음??","ㅇㅇ");
        gpsTracker = new GpsTracker(context);
        lat = gpsTracker.getLatitude();
        lon = gpsTracker.getLongitude();

        new Thread() {
            String currentTemp;
            String currentSky;
            @Override
            public void run() {


                Retrofit client = new Retrofit.Builder().baseUrl("https://api2.sktelecom.com/weather/").addConverterFactory(GsonConverterFactory.create()).build();
                RetrofitService service = client.create(RetrofitService.class);

                Call<RetrofitClient> call = service.currentweather("2", String.valueOf(lat), String.valueOf(lon));
                try {

                    Minutely minutely = call.execute().body().getWeather().getMinutely().get(0);
                    currentSky = minutely.getSky().getName();
                    Log.d("현재날씨정보", currentSky);
                    currentTemp = minutely.getTemperature().tc;


                } catch (IOException e) {
                    e.printStackTrace();
                }
                Bundle bundle = new Bundle();
                bundle.putString("weather",currentTemp);
                bundle.putString("sky", currentSky);

                Message msg = handler.obtainMessage();
                msg.setData(bundle);
                handler.handleMessage(msg);
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                    PushNotification.sendNotification(context, 1, PushNotification.Channel.MESSAGE, String.format("현재 날씨: " + cs + "  %s °C ", cw), "오늘 날씨에 맞는 옷을 확인해보세요.");
                    Log.d("뭐지","cs:"+cs+" cw: "+ cw);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }
    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            Bundle bun =msg.getData();
            cw = bun.getString("weather");
            cs = bun.getString("sky");

        }
    };

}
