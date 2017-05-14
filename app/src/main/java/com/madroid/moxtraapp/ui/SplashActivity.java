package com.madroid.moxtraapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.madroid.moxtraapp.AppConstants;
import com.madroid.moxtraapp.BaseActivity;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.LoginRequestDTO;
import com.madroid.moxtraapp.dtos.LoginResponseDTO;
import com.madroid.moxtraapp.ui.login.LoginActivity;
import com.madroid.moxtraapp.ui.login.LoginPresenter;
import com.madroid.moxtraapp.ui.login.LoginPresenterImpl;
import com.madroid.moxtraapp.ui.login.LoginView;
import java.util.Timer;
import java.util.TimerTask;

import utils.PreferencesUtils;

/**
 * Created by mohamed on 13/01/17.
 */
public class SplashActivity extends BaseActivity implements LoginView {

    public static final int SPLASH_TIME = 3000;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash);
        loginPresenter = new LoginPresenterImpl(this);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                if(PreferencesUtils.getInstance(SplashActivity.this).getBoolean(AppConstants.IS_LOGGED_IN)){
                    loginWithSavedData();
                }else {
                    //LocaleUtility.setUpApplicationLocale(SplashActivity.this, Constants.ENGLISH_LOCALE);
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);

                    finish();
                }
            }
        }, SPLASH_TIME);

    }


    private void loginWithSavedData(){

        loginPresenter.login(new LoginRequestDTO(PreferencesUtils.getInstance(SplashActivity.this).getString(AppConstants.USER_EMAIL),PreferencesUtils.getInstance(SplashActivity.this).getString(AppConstants.USER_PASSWORD)));

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {


    }

    @Override
    public void succededToLogin(LoginResponseDTO loginResponseDTO) {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
