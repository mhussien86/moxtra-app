package com.madroid.moxtraapp.ui.intro;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Window;
import android.view.WindowManager;

import com.madroid.moxtraapp.R;

import butterknife.ButterKnife;

public class AppIntroActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.common_activity);
        ButterKnife.bind(this);
        startAppIntroFragment();
    }

    private void startAppIntroFragment(){
        AppIntroFragment appIntroFragment = new AppIntroFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.layCommonActivity, appIntroFragment).commit();
    }


}
