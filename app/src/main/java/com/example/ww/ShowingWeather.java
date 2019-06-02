package com.example.ww;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Build;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.skydoves.elasticviews.ElasticButton;
import com.skydoves.elasticviews.ElasticImageView;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowingWeather extends AppCompatActivity {

    private final int PERMISSIONS_ACCESS_FINE_LOCATION = 1000;
    private final int PERMISSIONS_ACCESS_COARSE_LOCATION = 1001;
    private boolean isAccessFineLocation = false;
    private boolean isAccessCoarseLocation = false;
    private boolean isPermission = false;
    private String info;
    CoordinatorLayout layout;

    TextView tv_updateTime;
    TextView tv_currentLocation;
    TextView tv_currentTemp;
    TextView tv_minmaxTemp;
    TextView shortWeatherTime1;
    TextView shortWeatherTime2;
    TextView shortWeatherTime3;
    TextView shortWeatherTime4;
    TextView shortWeatherTime5;
    TextView shortWeatherTime6;
    TextView shortWeatherTime7;
    TextView shortWeatherTime8;
    TextView shortWeatherTime9;



    ImageView skyImage[];
    ImageView am[], pm[];
    LottieAnimationView currentWeather;

    TextView shortWeatherTemp1;
    TextView shortWeatherTemp2;
    TextView shortWeatherTemp3;
    TextView shortWeatherTemp4;
    TextView shortWeatherTemp5;
    TextView shortWeatherTemp6;
    TextView shortWeatherTemp7;
    TextView shortWeatherTemp8;
    TextView shortWeatherTemp9;

    TextView day1;
    ImageView longWeatherSky1am;
    ImageView longWeatherSky1pm;
    TextView longWeatherTemp1;
    TextView day2;
    TextView longWeatherTemp2;
    TextView day3;
    TextView longWeatherTemp3;
    TextView day4;
    TextView longWeatherTemp4;
    TextView day5;
    TextView longWeatherTemp5;
    TextView day6;
    TextView longWeatherTemp6;
    TextView day7;
    TextView longWeatherTemp7;

    String currentLocation;
    double lat;
    double lon;

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    ViewPager vp;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SlidingView sv = new SlidingView(this);
        View v1 = View.inflate(this, R.layout.activity_showing_weahter, null);
        sv.addView(v1);
        View v2 = View.inflate(this, R.layout.activity_look_book, null);
        sv.addView(v2);
        setContentView(sv);

        layout = findViewById(R.id.showingWeather);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.list_icon);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        tv_updateTime = findViewById(R.id.updateTime);
        tv_currentLocation = findViewById(R.id.curLoc);
        currentWeather = findViewById(R.id.currentWeatherPic);
        tv_currentTemp = findViewById(R.id.currentWeather);
        tv_minmaxTemp = findViewById(R.id.dailyMaxMin);

        shortWeatherTime1 = findViewById(R.id.shortWeatherTime1);
        shortWeatherTime2 = findViewById(R.id.shortWeatherTime2);
        shortWeatherTime3 = findViewById(R.id.shortWeatherTime3);
        shortWeatherTime4 = findViewById(R.id.shortWeatherTime4);
        shortWeatherTime5 = findViewById(R.id.shortWeatherTime5);
        shortWeatherTime6 = findViewById(R.id.shortWeatherTime6);
        shortWeatherTime7 = findViewById(R.id.shortWeatherTime7);
        shortWeatherTime8 = findViewById(R.id.shortWeatherTime8);
        shortWeatherTime9 = findViewById(R.id.shortWeatherTime9);

        skyImage = new ImageView[]{findViewById(R.id.shortWeatherStatus1), findViewById(R.id.shortWeatherStatus2),
                findViewById(R.id.shortWeatherStatus3), findViewById(R.id.shortWeatherStatus4),
                findViewById(R.id.shortWeatherStatus5), findViewById(R.id.shortWeatherStatus6),
                findViewById(R.id.shortWeatherStatus7), findViewById(R.id.shortWeatherStatus8),
                findViewById(R.id.shortWeatherStatus9)};

        shortWeatherTemp1 = findViewById(R.id.shortWeatherTemp1);
        shortWeatherTemp2 = findViewById(R.id.shortWeatherTemp2);
        shortWeatherTemp3 = findViewById(R.id.shortWeatherTemp3);
        shortWeatherTemp4 = findViewById(R.id.shortWeatherTemp4);
        shortWeatherTemp5 = findViewById(R.id.shortWeatherTemp5);
        shortWeatherTemp6 = findViewById(R.id.shortWeatherTemp6);
        shortWeatherTemp7 = findViewById(R.id.shortWeatherTemp7);
        shortWeatherTemp8 = findViewById(R.id.shortWeatherTemp8);
        shortWeatherTemp9 = findViewById(R.id.shortWeatherTemp9);

        am = new ImageView[]{findViewById(R.id.longWeatherSky2am), findViewById(R.id.longWeatherSky3am), findViewById(R.id.longWeatherSky4am), findViewById(R.id.longWeatherSky5am), findViewById(R.id.longWeatherSky6am), findViewById(R.id.longWeatherSky7am)};
        pm = new ImageView[]{findViewById(R.id.longWeatherSky2pm), findViewById(R.id.longWeatherSky3pm), findViewById(R.id.longWeatherSky4pm), findViewById(R.id.longWeatherSky5pm), findViewById(R.id.longWeatherSky6pm), findViewById(R.id.longWeatherSky7pm)};

        day1 = findViewById(R.id.day1);
        longWeatherSky1am = findViewById(R.id.longWeatherSky1am);
        longWeatherSky1pm = findViewById(R.id.longWeatherSky1pm);
        longWeatherTemp1 = findViewById(R.id.longWeatherTemp1);
        day2 = findViewById(R.id.day2);
        longWeatherTemp2 = findViewById(R.id.longWeatherTemp2);
        day3 = findViewById(R.id.day3);
        longWeatherTemp3 = findViewById(R.id.longWeatherTemp3);
        day4 = findViewById(R.id.day4);
        longWeatherTemp4 = findViewById(R.id.longWeatherTemp4);
        day5 = findViewById(R.id.day5);
        longWeatherTemp5 = findViewById(R.id.longWeatherTemp5);
        day6 = findViewById(R.id.day6);
        longWeatherTemp6 = findViewById(R.id.longWeatherTemp6);
        day7 = findViewById(R.id.day7);
        longWeatherTemp7 = findViewById(R.id.longWeatherTemp7);


        String nTime;
        nTime = getTime();
        tv_updateTime.setText(nTime);
        if (!isPermission)
            callPermission();

        Calendar c = Calendar.getInstance();
        try {
            c.setTime(mFormat.parse(nTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (6 < c.get(Calendar.HOUR_OF_DAY) && c.get(Calendar.HOUR_OF_DAY) < 19)
            layout.setBackgroundResource(R.drawable.day_background);
        else
            layout.setBackgroundResource(R.drawable.night_background);


        day1.setText(getDateDay(1));
        day2.setText(getDateDay(2));
        day3.setText(getDateDay(3));
        day4.setText(getDateDay(4));
        day5.setText(getDateDay(5));
        day6.setText(getDateDay(6));
        day7.setText(getDateDay(7));

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        lat = Double.valueOf(bundle.getString("lat"));
        lon = Double.valueOf(bundle.getString("lon"));

        currentLocation = getCurrentAddress(lat, lon);
        Log.d("위치 받아옴?", currentLocation);
        tv_currentLocation.setText(currentLocation);
        new ReceiveCurrentWeather().execute();
        new ReceiveShortWeather().execute();
        new ReceiveWeekWeather().execute();

        vp = (ViewPager) findViewById(R.id.vp);
        vp.setOnTouchListener(new View.OnTouchListener() {
                                  @Override
                                  public boolean onTouch(View v, MotionEvent event) {
                                      return true;
                                  }

                              }
        );
        ll = (LinearLayout) findViewById(R.id.ll);

        TextView tab_first = (TextView) findViewById(R.id.tab_first);
        TextView tab_second = (TextView) findViewById(R.id.tab_second);
        TextView tab_third = (TextView) findViewById(R.id.tab_third);
        TextView tab_fourth = (TextView) findViewById(R.id.tab_fourth);

        vp.setAdapter(new ShowingWeather.pagerAdapter(getSupportFragmentManager()));
        vp.setOffscreenPageLimit(0);
        vp.setCurrentItem(0);


        tab_first.setOnClickListener(movePageListener);
        tab_first.setTag(0);
        tab_second.setOnClickListener(movePageListener);
        tab_second.setTag(1);
        tab_third.setOnClickListener(movePageListener);
        tab_third.setTag(2);
        tab_fourth.setOnClickListener(movePageListener);
        tab_fourth.setTag(3);


        tab_first.setSelected(true);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int i = 0;
                while (i < 4) {
                    if (position == i) {
                        ll.findViewWithTag(i).setSelected(true);
                    } else {
                        ll.findViewWithTag(i).setSelected(false);
                    }
                    i++;


                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    View.OnClickListener movePageListener;

    {
        movePageListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = (int) v.getTag();

                int i = 0;
                while (i < 4) {
                    if (tag == i) {
                        ll.findViewWithTag(i).setSelected(true);
                    } else {
                        ll.findViewWithTag(i).setSelected(false);
                    }
                    i++;
                }


                vp.setCurrentItem(tag);
            }
        };

    }

    private class pagerAdapter extends FragmentStatePagerAdapter {
        public pagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new street();
                case 1:
                    return new campus();
                case 2:
                    return new casual();
                case 3:
                    return new office();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {

            return 4;
        }


    }

    @Override

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == PERMISSIONS_ACCESS_FINE_LOCATION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            isAccessFineLocation = true;
        } else if (requestCode == PERMISSIONS_ACCESS_COARSE_LOCATION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            isAccessCoarseLocation = true;
        }
        if (isAccessFineLocation && isAccessCoarseLocation) {
            isPermission = true;
        }
    }

    public String getCurrentAddress(double lat, double lon) {

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;

        try {
            addresses = geocoder.getFromLocation(lat, lon, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_SHORT).show();
            return "주소 미발견";
        } else {
            // GPS 를 사용할수 없으므로
            // gpsTracker.showSettingsAlert();
        }
        callPermission();  // 권한 요청을 해야 함

        Address address = addresses.get(0);
        return address.getAddressLine(0) + "\n";
    }

    private void callPermission() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_ACCESS_FINE_LOCATION);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSIONS_ACCESS_COARSE_LOCATION);

        } else {
            isPermission = true;
        }
    }

    public class ReceiveCurrentWeather extends AsyncTask<URL, Integer, Long> {
        String currentTemp;
        String dailyMax;
        String dailyMin;
        String currentSky;

        @Override
        protected Long doInBackground(URL... urls) {
            Retrofit client = new Retrofit.Builder().baseUrl("https://api2.sktelecom.com/weather/").addConverterFactory(GsonConverterFactory.create()).build();
            RetrofitService service = client.create(RetrofitService.class);

            Call<RetrofitClient> call = service.currentweather("2", String.valueOf(lat), String.valueOf(lon));
            try {

                Minutely minutely = call.execute().body().getWeather().getMinutely().get(0);
                currentSky = minutely.getSky().getName();
                Log.d("현재날씨정보", currentSky);
                currentTemp = minutely.getTemperature().tc;
                dailyMax = minutely.getTemperature().tmax;
                dailyMin = minutely.getTemperature().tmin;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Long aLong) {
            if (currentSky.equals("맑음")) {
                currentWeather.setAnimation("sun.json");
                currentWeather.setRepeatCount(82);
                currentWeather.playAnimation();
            }
            if (currentSky.equals("구름조금") || currentSky.equals("흐림")) {
                currentWeather.setAnimation("cloudy.json");
                currentWeather.setRepeatCount(180);
                currentWeather.playAnimation();
            }
            if (currentSky.equals("구름많음")) {
                currentWeather.setAnimation("clouds.json");
                currentWeather.setRepeatCount(180);
                currentWeather.playAnimation();
            }
            if (currentSky.equals("구름많고 비") || currentSky.equals("흐리고 비") || currentSky.equals("구름많고 비 또는 눈") || currentSky.equals("흐리고 비 또는 눈")) {
                currentWeather.setAnimation("rain.json");
                currentWeather.setRepeatCount(30);
                currentWeather.playAnimation();
            }
            if (currentSky.equals("구름많고 눈") || currentSky.equals("흐리고 눈")) {
                currentWeather.setAnimation("snow.json");
                currentWeather.setRepeatCount(180);
                currentWeather.playAnimation();
            }
            if (currentSky.equals("흐리고 낙뢰")) {
                currentWeather.setAnimation("storm.json");
                currentWeather.setRepeatCount(180);
                currentWeather.playAnimation();
            }
            if (currentSky.equals("뇌우/비") || currentSky.equals("뇌우/눈") || currentSky.equals("뇌우/비 또는 눈")) {
                currentWeather.setAnimation("thunder.json");
                currentWeather.setRepeatCount(180);
                currentWeather.playAnimation();
            }

            tv_currentTemp.setText(String.format("%s°", currentTemp));
            tv_minmaxTemp.setText(String.format("%s°/%s°", dailyMax, dailyMin));
        }
    }

    public class ReceiveShortWeather extends AsyncTask<URL, Integer, Long> {

        Forecast3days forecast3days;
        String temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9;
        String sky[];
        String tomorrowMin, tomorrowMax;
        double tMax, tMin;
        String timeRelease;

        protected Long doInBackground(URL... urls) {

            Retrofit client = new Retrofit.Builder().baseUrl("https://api2.sktelecom.com/weather/").addConverterFactory(GsonConverterFactory.create()).build();
            RetrofitService service = client.create(RetrofitService.class);

            Call<RetrofitClient> call = service.shortWeather("2", String.valueOf(lat), String.valueOf(lon));
            Log.d("뭘 보냄??", call.request().toString());
            Log.d("받아옴123?", lat + " " + lon);

            try {
                forecast3days = call.execute().body().getWeather().getForecast3days().get(0);
                timeRelease = forecast3days.getTimeRelease();
                Log.d("??", timeRelease);
                SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
                Calendar release = Calendar.getInstance();
                release.setTime(fm.parse(timeRelease));

                temp1 = forecast3days.getFcst3hour().getTemperature().getT4() + "°";
                temp2 = forecast3days.getFcst3hour().getTemperature().getT7() + "°";
                temp3 = forecast3days.getFcst3hour().getTemperature().getT10() + "°";
                temp4 = forecast3days.getFcst3hour().getTemperature().getT13() + "°";
                temp5 = forecast3days.getFcst3hour().getTemperature().getT16() + "°";
                temp6 = forecast3days.getFcst3hour().getTemperature().getT19() + "°";
                temp7 = forecast3days.getFcst3hour().getTemperature().getT22() + "°";
                temp8 = forecast3days.getFcst3hour().getTemperature().getT25() + "°";
                temp9 = forecast3days.getFcst3hour().getTemperature().getT28() + "°";

                Log.d("시간시간", String.valueOf(release.get(Calendar.HOUR_OF_DAY)));

                switch (release.get(Calendar.HOUR_OF_DAY)) {
                    case 2:
                        tomorrowMin = forecast3days.getFcst3hour().getSky().getN31();
                        tomorrowMax = forecast3days.getFcst3hour().getSky().getN37();
                        break;
                    case 5:
                        tomorrowMin = forecast3days.getFcst3hour().getSky().getN28();
                        tomorrowMax = forecast3days.getFcst3hour().getSky().getN34();
                        break;
                    case 8:
                        tomorrowMin = forecast3days.getFcst3hour().getSky().getN25();
                        tomorrowMax = forecast3days.getFcst3hour().getSky().getN31();
                        break;
                    case 11:
                        tomorrowMin = forecast3days.getFcst3hour().getSky().getN22();
                        tomorrowMax = forecast3days.getFcst3hour().getSky().getN28();
                        break;
                    case 14:
                        tomorrowMin = forecast3days.getFcst3hour().getSky().getN19();
                        tomorrowMax = forecast3days.getFcst3hour().getSky().getN25();
                        break;
                    case 17:
                        tomorrowMin = forecast3days.getFcst3hour().getSky().getN16();
                        tomorrowMax = forecast3days.getFcst3hour().getSky().getN22();
                        break;
                    case 20:
                        tomorrowMin = forecast3days.getFcst3hour().getSky().getN13();
                        tomorrowMax = forecast3days.getFcst3hour().getSky().getN19();
                        break;
                    case 23:
                        tomorrowMin = forecast3days.getFcst3hour().getSky().getN10();
                        tomorrowMax = forecast3days.getFcst3hour().getSky().getN16();
                        break;
                }

                sky = new String[]{forecast3days.getFcst3hour().getSky().getN4(), forecast3days.getFcst3hour().getSky().getN7(), forecast3days.getFcst3hour().getSky().getN10()
                        , forecast3days.getFcst3hour().getSky().getN13(), forecast3days.getFcst3hour().getSky().getN16(), forecast3days.getFcst3hour().getSky().getN19()
                        , forecast3days.getFcst3hour().getSky().getN22(), forecast3days.getFcst3hour().getSky().getN25(), forecast3days.getFcst3hour().getSky().getN28()};

                tMax = Double.valueOf(forecast3days.getFcstdaily().getTempMinMax().getTmax2day());
                tMin = Double.valueOf(forecast3days.getFcstdaily().getTempMinMax().getTmin2day());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Long result) {
            int time = Integer.valueOf(timeRelease.substring(11, 13));
            String[] t = new String[9];
            time = time + 4;
            for (int i = 0; i < 9; i++) {
                if (((time / 12) % 2) == 0) {
                    if (time % 12 == 0)
                        t[i] = "오전 12시";
                    else
                        t[i] = "오전 " + (time % 12) + "시";
                } else {
                    if (time % 12 == 0)
                        t[i] = "오후 12시";
                    else
                        t[i] = "오후 " + (time % 12) + "시";
                }
                time = time + 3;
            }

            shortWeatherTime1.setText(t[0]);
            shortWeatherTime2.setText(t[1]);
            shortWeatherTime3.setText(t[2]);
            shortWeatherTime4.setText(t[3]);
            shortWeatherTime5.setText(t[4]);
            shortWeatherTime6.setText(t[5]);
            shortWeatherTime7.setText(t[6]);
            shortWeatherTime8.setText(t[7]);
            shortWeatherTime9.setText(t[8]);

            shortWeatherTemp1.setText(temp1);
            shortWeatherTemp2.setText(temp2);
            shortWeatherTemp3.setText(temp3);
            shortWeatherTemp4.setText(temp4);
            shortWeatherTemp5.setText(temp5);
            shortWeatherTemp6.setText(temp6);
            shortWeatherTemp7.setText(temp7);
            shortWeatherTemp8.setText(temp8);
            shortWeatherTemp9.setText(temp9);

            for (int i = 0; i < 9; i++) {
                if (sky[i].equals("맑음"))
                    skyImage[i].setImageResource(R.drawable.sun);
                if (sky[i].equals("구름조금"))
                    skyImage[i].setImageResource(R.drawable.cloudy);
                if (sky[i].equals("구름많음"))
                    skyImage[i].setImageResource(R.drawable.clouds);
                if (sky[i].equals("구름많고 비") || sky[i].equals("구름많고 비 또는 눈"))
                    skyImage[i].setImageResource(R.drawable.rainy);
                if (sky[i].equals("구름많고 눈"))
                    skyImage[i].setImageResource(R.drawable.snowy);
                if (sky[i].equals("흐림"))
                    skyImage[i].setImageResource(R.drawable.cloud);
                if (sky[i].equals("흐리고 비") || sky[i].equals("흐리고 비 또는 눈"))
                    skyImage[i].setImageResource(R.drawable.rainy);
                if (sky[i].equals("흐리고 눈"))
                    skyImage[i].setImageResource(R.drawable.snowy);
                if (sky[i].equals("흐리고 낙뢰"))
                    skyImage[i].setImageResource(R.drawable.strom);
                if (sky[i].equals("뇌우/비") || sky[i].equals("뇌우/눈"))
                    skyImage[i].setImageResource(R.drawable.thunderstorm);
                if (sky[i].equals("뇌우/비 또는 눈"))
                    skyImage[i].setImageResource(R.drawable.thunder);
            }

            if (tomorrowMin.equals("맑음"))
                longWeatherSky1am.setImageResource(R.drawable.sun);
            if (tomorrowMin.equals("구름조금"))
                longWeatherSky1am.setImageResource(R.drawable.cloudy);
            if (tomorrowMin.equals("구름많음"))
                longWeatherSky1am.setImageResource(R.drawable.clouds);
            if (tomorrowMin.equals("구름많고 비") || tomorrowMin.equals("구름많고 비 또는 눈"))
                longWeatherSky1am.setImageResource(R.drawable.rainy);
            if (tomorrowMin.equals("구름많고 눈"))
                longWeatherSky1am.setImageResource(R.drawable.snowy);
            if (tomorrowMin.equals("흐림"))
                longWeatherSky1am.setImageResource(R.drawable.cloud);
            if (tomorrowMin.equals("흐리고 비") || tomorrowMin.equals("흐리고 비 또는 눈"))
                longWeatherSky1am.setImageResource(R.drawable.rainy);
            if (tomorrowMin.equals("흐리고 눈"))
                longWeatherSky1am.setImageResource(R.drawable.snowy);
            if (tomorrowMin.equals("흐리고 낙뢰"))
                longWeatherSky1am.setImageResource(R.drawable.strom);
            if (tomorrowMin.equals("뇌우/비"))
                longWeatherSky1am.setImageResource(R.drawable.thunderstorm);
            if (tomorrowMin.equals("뇌우/눈"))
                longWeatherSky1am.setImageResource(R.drawable.thunderstorm);
            if (tomorrowMin.equals("뇌우/비 또는 눈"))
                longWeatherSky1am.setImageResource(R.drawable.thunder);

            if (tomorrowMax.equals("맑음"))
                longWeatherSky1pm.setImageResource(R.drawable.sun);
            if (tomorrowMax.equals("구름조금"))
                longWeatherSky1pm.setImageResource(R.drawable.cloudy);
            if (tomorrowMax.equals("구름많음"))
                longWeatherSky1pm.setImageResource(R.drawable.clouds);
            if (tomorrowMax.equals("구름많고 비") || tomorrowMax.equals("구름많고 비 또는 눈"))
                longWeatherSky1pm.setImageResource(R.drawable.rainy);
            if (tomorrowMax.equals("구름많고 눈"))
                longWeatherSky1pm.setImageResource(R.drawable.snowy);
            if (tomorrowMax.equals("흐림"))
                longWeatherSky1pm.setImageResource(R.drawable.cloud);
            if (tomorrowMax.equals("흐리고 비") || tomorrowMax.equals("흐리고 비 또는 눈"))
                longWeatherSky1pm.setImageResource(R.drawable.rainy);
            if (tomorrowMax.equals("흐리고 눈"))
                longWeatherSky1pm.setImageResource(R.drawable.snowy);
            if (tomorrowMax.equals("흐리고 낙뢰"))
                longWeatherSky1pm.setImageResource(R.drawable.strom);
            if (tomorrowMax.equals("뇌우/비"))
                longWeatherSky1pm.setImageResource(R.drawable.thunderstorm);
            if (tomorrowMax.equals("뇌우/눈"))
                longWeatherSky1pm.setImageResource(R.drawable.thunderstorm);
            if (tomorrowMax.equals("뇌우/비 또는 눈"))
                longWeatherSky1pm.setImageResource(R.drawable.thunder);

            longWeatherTemp1.setText(String.format("%s°/%s°", (int) tMax, (int) tMin));
        }

    }

    public static String getDateDay(int offset) {
        String day = "";
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, offset);
        int dayNum = cal.get(Calendar.DAY_OF_WEEK);
        switch (dayNum) {
            case 1:
                day = "일";
                break;
            case 2:
                day = "월";
                break;
            case 3:
                day = "화";
                break;
            case 4:
                day = "수";
                break;
            case 5:
                day = "목";
                break;
            case 6:
                day = "금";
                break;
            case 7:
                day = "토";
                break;
        }
        return day;
    }

    public class ReceiveWeekWeather extends AsyncTask<URL, Integer, Long> {

        Forecast6days forecast6days;
        String temp1, temp2, temp3, temp4, temp5, temp6;
        String skyAm[], skyPm[];
        String timeRelease;


        @Override
        protected Long doInBackground(URL... urls) {
            Retrofit client = new Retrofit.Builder().baseUrl("https://api2.sktelecom.com/weather/").addConverterFactory(GsonConverterFactory.create()).build();
            RetrofitService service = client.create(RetrofitService.class);

            Call<RetrofitClient> call = service.longWeather("2", String.valueOf(lat), String.valueOf(lon));
            try {
                forecast6days = call.execute().body().getWeather().getForecast6days().get(0);
                timeRelease = forecast6days.getTimeRelease();

                temp1 = forecast6days.getTemperature().getTmax2day() + "°/" + forecast6days.getTemperature().getTmin2day() + "°";
                temp2 = forecast6days.getTemperature().getTmax3day() + "°/" + forecast6days.getTemperature().getTmin3day() + "°";
                temp3 = forecast6days.getTemperature().getTmax4day() + "°/" + forecast6days.getTemperature().getTmin4day() + "°";
                temp4 = forecast6days.getTemperature().getTmax5day() + "°/" + forecast6days.getTemperature().getTmin5day() + "°";
                temp5 = forecast6days.getTemperature().getTmax6day() + "°/" + forecast6days.getTemperature().getTmin6day() + "°";
                temp6 = forecast6days.getTemperature().getTmax7day() + "°/" + forecast6days.getTemperature().getTmin7day() + "°";


                skyAm = new String[]{forecast6days.getSky().getAm2day(), forecast6days.getSky().getAm3day(), forecast6days.getSky().getAm4day(), forecast6days.getSky().getAm5day(), forecast6days.getSky().getAm6day(), forecast6days.getSky().getAm7day()};
                skyPm = new String[]{forecast6days.getSky().getPm2day(), forecast6days.getSky().getPm3day(), forecast6days.getSky().getPm4day(), forecast6days.getSky().getPm5day(), forecast6days.getSky().getPm6day(), forecast6days.getSky().getPm7day()};

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Long aLong) {
            for (int i = 0; i < 6; i++) {
                if (skyAm[i].equals("맑음"))
                    am[i].setImageResource(R.drawable.sun);
                if (skyAm[i].equals("구름조금"))
                    am[i].setImageResource(R.drawable.cloudy);
                if (skyAm[i].equals("구름많음"))
                    am[i].setImageResource(R.drawable.clouds);
                if (skyAm[i].equals("구름많고 비") || skyAm[i].equals("구름많고 비 또는 눈"))
                    am[i].setImageResource(R.drawable.rainy);
                if (skyAm[i].equals("구름많고 눈"))
                    am[i].setImageResource(R.drawable.snowy);
                if (skyAm[i].equals("흐림"))
                    am[i].setImageResource(R.drawable.cloud);
                if (skyAm[i].equals("흐리고 비") || skyAm[i].equals("흐리고 비 또는 눈"))
                    am[i].setImageResource(R.drawable.rainy);
                if (skyAm[i].equals("흐리고 눈"))
                    am[i].setImageResource(R.drawable.snowy);
                if (skyAm[i].equals("흐리고 낙뢰"))
                    am[i].setImageResource(R.drawable.strom);
                if (skyAm[i].equals("뇌우/비"))
                    am[i].setImageResource(R.drawable.thunderstorm);
                if (skyAm[i].equals("뇌우/눈"))
                    skyImage[i].setImageResource(R.drawable.thunderstorm);
                if (skyAm[i].equals("뇌우/비 또는 눈"))
                    am[i].setImageResource(R.drawable.thunder);
            }

            for (int i = 0; i < 6; i++) {
                if (skyPm[i].equals("맑음"))
                    pm[i].setImageResource(R.drawable.sun);
                if (skyPm[i].equals("구름조금"))
                    pm[i].setImageResource(R.drawable.cloudy);
                if (skyPm[i].equals("구름많음"))
                    pm[i].setImageResource(R.drawable.clouds);
                if (skyPm[i].equals("구름많고 비") || skyPm[i].equals("구름많고 비 또는 눈"))
                    pm[i].setImageResource(R.drawable.rainy);
                if (skyPm[i].equals("구름많고 눈"))
                    pm[i].setImageResource(R.drawable.snowy);
                if (skyPm[i].equals("흐림"))
                    pm[i].setImageResource(R.drawable.cloud);
                if (skyPm[i].equals("흐리고 비") || skyPm[i].equals("흐리고 비 또는 눈"))
                    pm[i].setImageResource(R.drawable.rainy);
                if (skyPm[i].equals("흐리고 눈"))
                    pm[i].setImageResource(R.drawable.snowy);
                if (skyPm[i].equals("흐리고 낙뢰"))
                    pm[i].setImageResource(R.drawable.strom);
                if (skyPm[i].equals("뇌우/비"))
                    pm[i].setImageResource(R.drawable.thunderstorm);
                if (skyPm[i].equals("뇌우/눈"))
                    skyImage[i].setImageResource(R.drawable.thunderstorm);
                if (skyPm[i].equals("뇌우/비 또는 눈"))
                    pm[i].setImageResource(R.drawable.thunder);
            }
            longWeatherTemp2.setText(temp1);
            longWeatherTemp3.setText(temp2);
            longWeatherTemp4.setText(temp3);
            longWeatherTemp5.setText(temp4);
            longWeatherTemp6.setText(temp5);
            longWeatherTemp7.setText(temp6);
        }

    }

    private String getTime() {
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home: {
                Intent intent = new Intent(this, Place.class);
                startActivity(intent);
                return true;
            }
            case R.id.menu: {
                SettingDialog dialog = new SettingDialog(this);
                dialog.show();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
