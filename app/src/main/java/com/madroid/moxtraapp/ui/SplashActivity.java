package com.madroid.moxtraapp.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.madroid.moxtraapp.AppConstants;
import com.madroid.moxtraapp.BaseActivity;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.LoginRequestDTO;
import com.madroid.moxtraapp.dtos.LoginResponseDTO;
import com.madroid.moxtraapp.ui.intro.AppIntroActivity;
import com.madroid.moxtraapp.ui.login.LoginPresenter;
import com.madroid.moxtraapp.ui.login.LoginPresenterImpl;
import com.madroid.moxtraapp.ui.login.LoginView;
import com.moxtra.sdk.MXAccountManager;
import com.moxtra.sdk.MXSDKConfig;
import com.moxtra.sdk.MXSDKException;

import org.parceler.Parcels;

import java.io.IOException;
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
        if (PreferencesUtils.getInstance(SplashActivity.this).getBoolean(AppConstants.IS_LOGGED_IN)) {
            loginWithSavedData();

        } else {

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {

//                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);

                    Intent intent = new Intent(SplashActivity.this, AppIntroActivity.class);

                    startActivity(intent);

                    finish();

                }
            }, SPLASH_TIME);
        }
    }


    private void loginWithSavedData() {

        loginPresenter.login(new LoginRequestDTO(PreferencesUtils.getInstance(SplashActivity.this).getString(AppConstants.USER_EMAIL), PreferencesUtils.getInstance(SplashActivity.this).getString(AppConstants.USER_PASSWORD)));

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

    private static final String TAG = "MoxieChatApplication";

    @Override
    public void succededToLogin(final LoginResponseDTO loginResponseDTO) {
        try {
            MXAccountManager.createInstance(SplashActivity.this, loginResponseDTO.getResponse().getClientId(), loginResponseDTO.getResponse().clientSecret, true);

        } catch (MXSDKException.InvalidParameter invalidParameter) {
            Log.e(TAG, "Error when creating MXAccountManager instance.", invalidParameter);
        }

        try {
            LoginResponseDTO.UserData user = loginResponseDTO.getResponse().getUserData();
            Bitmap avatar = BitmapFactory.decodeStream(SplashActivity.this.getAssets().open("A01.png"));
            final MXSDKConfig.MXUserInfo mxUserInfo = new MXSDKConfig.MXUserInfo(user.email, MXSDKConfig.MXUserIdentityType.IdentityUniqueId);
            final MXSDKConfig.MXProfileInfo mxProfileInfo = new MXSDKConfig.MXProfileInfo(user.firstName, user.lastName, avatar);
            showProgress();

            MXAccountManager.getInstance().setupUser(mxUserInfo, mxProfileInfo, user.getMoxtraOrgId(), null, new MXAccountManager.MXAccountLinkListener() {
                @Override
                public void onLinkAccountDone(boolean success) {
                    if (success) {
                        hideProgress();
                        Log.i(TAG, "Linked to moxtra account successfully.");
                        Log.e("Accecss token", loginResponseDTO.getResponse().getAccessToken());

                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        Bundle b = new Bundle();
                        b.putParcelable(AppConstants.LOGIN_RESPONSE, Parcels.wrap(loginResponseDTO));
                        intent.putExtra("bundle", b);
                        startActivity(intent);
                        finish();
                    } else {
                        hideProgress();
                        Toast.makeText(SplashActivity.this, "Failed to setup moxtra user.", Toast.LENGTH_LONG).show();
                        Log.e(TAG, "Failed to setup moxtra user.");
                    }
                }
            });
        } catch (IOException e) {
            Log.e(TAG, "Can't decode avatar.", e);
        }

    }
}
