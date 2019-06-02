package com.example.ww;

import android.location.Address;
import android.location.Geocoder;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;


import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class PlaceSearch extends AppCompatActivity {

    String UserPlaceName;
    double UserLatitude;
    double UserLongitude;

    private DbOpenHelper mDbOpenHelper;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_search);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);


        final TextView tv =  findViewById(R.id.textView4); // 결과창
        Button b2 = findViewById(R.id.button2);
        final EditText et3 = findViewById(R.id.editText3);

        // 지오코딩(GeoCoding) : 주소,지명 => 위도,경도 좌표로 변환
        //     위치정보를 얻기위한 권한을 획득, AndroidManifest.xml
        //    ACCESS_FINE_LOCATION : 현재 나의 위치를 얻기 위해서 필요함
        //    INTERNET : 구글서버에 접근하기위해서 필요함

        final Geocoder geocoder = new Geocoder(this);
        final String sort = "place";


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Address>list = null;


                String str = et3.getText().toString();
                UserPlaceName = str;
                try {
                    list = geocoder.getFromLocationName(
                            str, // 지역 이름
                            10); // 읽을 개수
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("test","입출력 오류 - 서버에서 주소변환시 에러발생");
                }

                if (list != null) {
                    if (list.size() == 0) {
                        tv.setText("해당되는 주소 정보는 없습니다");
                    } else {
                        tv.setText(list.get(0).getAddressLine(0));
                        UserLatitude = list.get(0).getLatitude();
                        UserLongitude = list.get(0).getLongitude();
                        //          list.get(0).getCountryName();  // 국가명
                        //          list.get(0).getLatitude();        // 위도
                        //          list.get(0).getLongitude();    // 경도
                    }
                }
            }
        });

        mDbOpenHelper = new DbOpenHelper(this);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userLatitude;
                String userLongitude;

                userLatitude = Double.toString(UserLatitude);
                userLongitude = Double.toString(UserLongitude);
                mDbOpenHelper.open();
                mDbOpenHelper.insertColumn(UserPlaceName, userLatitude, userLongitude);
                finish();
            }
        });

    }

}
