package com.androidschool.denis.okuma;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends Activity {
    private final int SPLASH_DISPLAY_LENGHT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        System.out.println("onCreate() of SplashScreenActivity");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                //Intent mainIntent = new Intent(SplashScreenActivity.this, InitialActivity.class);
                Intent mainIntent = new Intent(SplashScreenActivity.this, ListGroupActivity.class);
                SplashScreenActivity.this.startActivity(mainIntent);
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}

