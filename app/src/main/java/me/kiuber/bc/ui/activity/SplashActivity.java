package me.kiuber.bc.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import java.util.TimerTask;

import cn.bmob.v3.Bmob;
import cn.smssdk.SMSSDK;
import me.kiuber.bc.R;


/**
 * Created by Kiuber on 2017/2/26 0026.
 */

public class SplashActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGHT = 3000; //延迟三秒

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initBmob();
        initMob();
        jump2Main();
    }

    private void initMob() {
//        SMSSDK.initSDK(this, "1ba78e857a348", "e388c8d4fe41d358fcbeb83a089ee170");
        SMSSDK.initSDK(this, "139216e4958f6", "63512a2fcc9c9e2f5c00bbdce60d920e");
    }

    private void initBmob() {
        Bmob.initialize(this, "de841c91ae694abb3e31868c64d0a1c7", "Bmob");
    }

    private void jump2Main() {
        new Handler().postDelayed(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
}
