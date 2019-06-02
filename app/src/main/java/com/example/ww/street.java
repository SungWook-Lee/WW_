package com.example.ww;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;


import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;


import com.github.chrisbanes.photoview.PhotoView;
import com.skydoves.elasticviews.ElasticButton;

import java.io.ByteArrayOutputStream;
import java.util.Random;


public class street extends Fragment {
    public static final int imgs[] =
            {R.drawable.ss1, R.drawable.ss2, R.drawable.ss3, R.drawable.ss4, R.drawable.ss5, R.drawable.ss6, R.drawable.ss7,
                    R.drawable.ss8, R.drawable.ss9, R.drawable.ss10, R.drawable.ss11, R.drawable.ss12, R.drawable.ss13, R.drawable.ss14,
                    R.drawable.ss15, R.drawable.ss16, R.drawable.ss17, R.drawable.ss18, R.drawable.ss19};
    public static final int imgf[] = {R.drawable.sf1, R.drawable.sf2, R.drawable.sf3, R.drawable.sf4,
            R.drawable.sf5, R.drawable.sf6, R.drawable.sf7, R.drawable.sf8};

    private LayoutInflater inflater;
    private RelativeLayout view;
    private ViewGroup container;
    private Bundle savedInstanceState;
    private boolean isFragment = true;
    private int index ;
    private int index1; private int index2; private int index3; private int index4; private int index5 ;private int index6; private int index7;


    private int temp;
    public street() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = (RelativeLayout) inflater.inflate(R.layout.fragment_street, container, false);
       random();
        ImageButton btn1 = (ImageButton) view.findViewById(R.id.elasticimageview);
       btn1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               random();
           }
       });

       return view;
    }



    public void random(){
        int[] num = new int[4];
        Random r = new Random();
        for (int a = 0; a < 4; a++) {
            num[a] = r.nextInt(19);
            for (int j = 0; j < a; j++) {
                if (num[a] == num[j]) {
                    a--;
                }
            }}
        PhotoView img1 = (PhotoView) view.findViewById(R.id.im1);
        img1.setImageResource(imgs[num[0]]);
        index = num[0];
        img1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ImgView.class);
                Bitmap send = BitmapFactory.decodeResource(getResources(),imgs[index]);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                send.compress(Bitmap.CompressFormat.JPEG,100,stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("image",byteArray);
                startActivity(intent);
            }
        });



        PhotoView img2 = (PhotoView) view.findViewById(R.id.im2);
        img2.setImageResource(imgs[num[1]]);

        index1 = num[1];
        img2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ImgView.class);
                Bitmap send = BitmapFactory.decodeResource(getResources(),imgs[index1]);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                send.compress(Bitmap.CompressFormat.JPEG,100,stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("image",byteArray);
                startActivity(intent);
            }
        });


        PhotoView img3 = (PhotoView) view.findViewById(R.id.im3);
        img3.setImageResource(imgs[num[2]]);
        index2 = num[2];
        img3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ImgView.class);
                Bitmap send = BitmapFactory.decodeResource(getResources(),imgs[index2]);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                send.compress(Bitmap.CompressFormat.JPEG,100,stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("image",byteArray);
                startActivity(intent);
            }
        });


        PhotoView img4 = (PhotoView) view.findViewById(R.id.im4);
        img4.setImageResource(imgs[num[3]]);
        index3 = num[3];
        img4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ImgView.class);
                Bitmap send = BitmapFactory.decodeResource(getResources(),imgs[index3]);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                send.compress(Bitmap.CompressFormat.JPEG,100,stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("image",byteArray);
                startActivity(intent);
            }
        });
        int[] num1 = new int[4];
        Random r1 = new Random();
        for (int z = 0; z < 4; z++) {
            num1[z] = r1.nextInt(8);
            for (int k = 0; k < z; k++) {
                if (num1[z] == num1[k]) {
                    z--;

                }
            }
        }

        PhotoView img5 = (PhotoView) view.findViewById(R.id.im5);
        img5.setImageResource(imgf[num1[0]]);
        index4 = num1[0];
        img5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ImgView.class);
                Bitmap send = BitmapFactory.decodeResource(getResources(),imgf[index4]);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                send.compress(Bitmap.CompressFormat.JPEG,100,stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("image",byteArray);
                startActivity(intent);
            }
        });


        PhotoView img6 = (PhotoView) view.findViewById(R.id.im6);
        img6.setImageResource(imgf[num1[1]]);
        index5 = num1[1];
        img6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ImgView.class);
                Bitmap send = BitmapFactory.decodeResource(getResources(),imgf[index5]);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                send.compress(Bitmap.CompressFormat.JPEG,100,stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("image",byteArray);
                startActivity(intent);
            }
        });


        PhotoView img7 = (PhotoView) view.findViewById(R.id.im7);
        img7.setImageResource(imgf[num1[2]]);
        index6 = num1[2];
        img7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ImgView.class);
                Bitmap send = BitmapFactory.decodeResource(getResources(),imgf[index6]);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                send.compress(Bitmap.CompressFormat.JPEG,100,stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("image",byteArray);
                startActivity(intent);
            }
        });


        PhotoView img8 = (PhotoView) view.findViewById(R.id.im8);
        img8.setImageResource(imgf[num1[3]]);
        index7 = num1[3];
        img8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ImgView.class);
                Bitmap send = BitmapFactory.decodeResource(getResources(),imgf[index7]);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                send.compress(Bitmap.CompressFormat.JPEG,100,stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("image",byteArray);
                startActivity(intent);
            }
        });



    }


}


