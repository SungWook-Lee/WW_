package com.example.ww;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SettingDialog extends Dialog {

    private static final int LAYOUT = R.layout.setting;
    private Context context;

    int hour = 0, min = 0, h, m;

    public SettingDialog(Context context) {
        super(context);
        this.context = context;
    }

    public SettingDialog(Context context, String name) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        final NumberPicker NmAmPm = findViewById(R.id.amPm);
        final NumberPicker NmHour = findViewById(R.id.alarm_hour);
        final NumberPicker NmMin = findViewById(R.id.alarm_min);
        Button btnOk = findViewById(R.id.btn_ok);



        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("HHmm");
        final String formatDate = sdfNow.format(date);

        h = Integer.parseInt(formatDate.substring(0, 2));
        m = Integer.parseInt(formatDate.substring(2, 4));

        NmAmPm.setMinValue(0);
        NmAmPm.setMaxValue(1);
        NmAmPm.setDisplayedValues(new String[]{"오전", "오후"});

        NmHour.setMinValue(1);
        NmHour.setMaxValue(12);
        NmHour.setValue(h);
        NmHour.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        NmMin.setMinValue(00);
        NmMin.setMaxValue(59);
        NmMin.setValue(m);
        NmHour.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            PushNotification.createChannel(getContext());
        }






        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hour = NmHour.getValue();
                min = NmMin.getValue();
                if(NmAmPm.getValue()==1){
                    hour = hour + 12;
                }
                AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
                Log.d("예약됨??",am.toString());
                Intent intent = new Intent(context, NotificationReceiver.class);
                PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);

                Calendar calendar = Calendar.getInstance();
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), hour, min);
                long time = calendar.getTimeInMillis();
                SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Log.d("예약시간", mFormat.format(time));

                am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
                Log.d("예약됨???",am.toString());

                //                PushNotification.sendNotification(getContext(), 1, PushNotification.Channel.MESSAGE, String.format("현재 날씨: " + currentSky +"  %s °C ",currentTemp), "오늘 날씨에 맞는 옷을 확인해보세요.", time);
            }
        });

//        public void goToNotificationSettings() {
//            Intent i = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
//            i.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
//            startActivity(i);
//        }
//
//        /**
//         * Send intent to load system Notification Settings UI for a particular channel.
//         *
//         * @param channel Name of channel to configure
//         */
//        public void goToNotificationSettings(String channel) {
//            Intent i = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
//            i.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
//            i.putExtra(Settings.EXTRA_CHANNEL_ID, channel);
//            startActivity(i);
//        }
    }

}
