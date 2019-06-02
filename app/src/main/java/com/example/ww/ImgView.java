package com.example.ww;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.skydoves.elasticviews.ElasticButton;
import com.skydoves.elasticviews.ElasticImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class ImgView extends AppCompatActivity {
    private static final String PICTURE_FOLDER = "DCIM";
    private ElasticButton cap;
    private ElasticButton back;
    private View capview;
    RelativeLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imgview);
        Intent intent = getIntent();
        byte[] arr = getIntent().getByteArrayExtra("image");
        Bitmap image = BitmapFactory.decodeByteArray(arr, 0, arr.length);
        PhotoView bigphoto = (PhotoView) findViewById(R.id.clickimg);
        bigphoto.setImageBitmap(image);


        cap = (ElasticButton) findViewById(R.id.save);
        back = (ElasticButton) findViewById(R.id.back);
        capview = getWindow().getDecorView();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        cap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnCaptureClick(capview);
            }


            public void mOnCaptureClick(View v) {

                View rootView = getWindow().getDecorView();

                File screenShot = ScreenShot(rootView);
                Toast.makeText(getApplicationContext(),"SAVE!",Toast.LENGTH_LONG).show();
                if (screenShot != null) {

                    sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(screenShot)));
                }
            }

            //화면 캡쳐하기
            public File ScreenShot(View view) {
                view.setDrawingCacheEnabled(true);

                Bitmap screenBitmap = view.getDrawingCache();

                String filename = "screenshot.png";
                File file = new File(Environment.getExternalStorageDirectory() + "/DCIM", filename);
                FileOutputStream os = null;
                try {
                    os = new FileOutputStream(file);
                    screenBitmap.compress(Bitmap.CompressFormat.PNG, 90, os);
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }

                view.setDrawingCacheEnabled(false);
                return file;
            }

        });


    }
}





