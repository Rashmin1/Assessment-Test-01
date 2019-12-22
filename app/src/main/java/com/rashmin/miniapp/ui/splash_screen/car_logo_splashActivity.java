package com.rashmin.miniapp.ui.splash_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import com.rashmin.miniapp.R;
import com.rashmin.miniapp.ui.home_page.HomeActivity;

public class car_logo_splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_logo_splash);

        CountDownTimer timer = new CountDownTimer(5000, 500) {
            @Override
            public void onTick(long millisUntilFinished) {

                Log.d("Checktick","skjdhnkucgbfuil");

            }

            @Override
            public void onFinish() {


                Intent intent = new Intent(car_logo_splashActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();

            }
        };
        timer.start();
    }
}