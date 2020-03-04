package com.androidschool.denis.okuma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import static com.androidschool.denis.okuma.AlarmManager.ADDRESS_ALARM_00;
import static com.androidschool.denis.okuma.AlarmManager.ADDRESS_ALARM_85;
import static com.androidschool.denis.okuma.AlarmManager.ADDRESS_ALARM_85000;
import static com.androidschool.denis.okuma.AlarmManager.ADDRESS_ALARM_90000;

import static com.androidschool.denis.okuma.AlarmManager.ADDRESS_ALARM_98;
import static com.androidschool.denis.okuma.AlarmManager.ADDRESS_ALARM_A;
import static com.androidschool.denis.okuma.AlarmManager.ADDRESS_ALARM_B;
import static com.androidschool.denis.okuma.AlarmManager.ADDRESS_ALARM_C;
import static com.androidschool.denis.okuma.AlarmManager.ADDRESS_ALARM_D;
import static com.androidschool.denis.okuma.AlarmManager.ADDRESS_ALARM_ERR;
import static com.androidschool.denis.okuma.AlarmManager.ADDRESS_MCD;
import static com.androidschool.denis.okuma.AlarmManager.PATTERN_ALARM;
import static com.androidschool.denis.okuma.AlarmManager.PATTERN_MCD;
import static com.androidschool.denis.okuma.AlarmManager.SPLITTER_ALARM;
import static com.androidschool.denis.okuma.AlarmManager.SPLITTER_MCD;
import static com.androidschool.denis.okuma.AlarmManager.TYPE_ALARM;
import static com.androidschool.denis.okuma.AlarmManager.TYPE_MCD;


public class InitialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        System.out.println("onCreate() of InitialActivity");
    }

    public void click(View view) {
        Class targetActivity = MainActivity.class;
        String filename = "";
        String splitter = "";
        String pattern = "";
        String type = TYPE_ALARM;
        int id = view.getId();

        switch (id) {
            case R.id.bntAlarm00:
                filename = ADDRESS_ALARM_00;
                splitter = SPLITTER_ALARM;
                pattern = PATTERN_ALARM;
                break;


            case R.id.bntAlarmA:
                filename = ADDRESS_ALARM_A;
                splitter = SPLITTER_ALARM;
                pattern = PATTERN_ALARM;
                break;

            case R.id.bntAlarmB:
                filename = ADDRESS_ALARM_B;
                splitter = SPLITTER_ALARM;
                pattern = PATTERN_ALARM;
                break;

            case R.id.bntAlarmC:
                filename = ADDRESS_ALARM_C;
                splitter = SPLITTER_ALARM;
                pattern = PATTERN_ALARM;
                break;

            case R.id.bntAlarmD:
                filename = ADDRESS_ALARM_D;
                splitter = SPLITTER_ALARM;
                pattern = PATTERN_ALARM;
                break;

            case R.id.bntAlarmERR:
                filename = ADDRESS_ALARM_ERR;
                splitter = SPLITTER_ALARM;
                pattern = PATTERN_ALARM;
                break;


            case R.id.bntAlarm85:
                filename = ADDRESS_ALARM_85;
                splitter = SPLITTER_ALARM;
                pattern = PATTERN_ALARM;
                break;


            case R.id.bntAlarm98:
                filename = ADDRESS_ALARM_98;
                splitter = SPLITTER_ALARM;
                pattern = PATTERN_ALARM;
                break;

            case R.id.bntAlarm85000:
                filename = ADDRESS_ALARM_85000;
                splitter = SPLITTER_ALARM;
                pattern = PATTERN_ALARM;
                break;

            case R.id.bntAlarm90000:
                filename = ADDRESS_ALARM_90000;
                splitter = SPLITTER_ALARM;
                pattern = PATTERN_ALARM;
                break;



            case R.id.bntMCD:
                filename = ADDRESS_MCD;
                //filename = ADDRESS_ALARM_85;
                splitter = SPLITTER_MCD;
                pattern = PATTERN_MCD;
                type = TYPE_MCD;
                break;

        }
        Intent intent = new Intent(InitialActivity.this, targetActivity);
        intent.putExtra("fileName", filename);
        intent.putExtra("splitter", splitter);
        intent.putExtra("pattern", pattern);
        intent.putExtra("type", type);
        startActivity(intent);
    }
}
