package com.madroid.moxtraapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.madroid.moxtraapp.BaseActivity;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.ui.login.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mohamed on 13/01/17.
 */
public class SplashActivity extends BaseActivity {

    public static final int SPLASH_TIME = 3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                //LocaleUtility.setUpApplicationLocale(SplashActivity.this, Constants.ENGLISH_LOCALE);
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);

                finish();

            }
        }, SPLASH_TIME);

    }
}
