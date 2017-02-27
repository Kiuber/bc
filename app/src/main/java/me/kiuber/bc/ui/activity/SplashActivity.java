package me.kiuber.bc.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import java.util.TimerTask;

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
        jump2Main();
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
