package com.madroid.moxtraapp.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import com.madroid.moxtraapp.BaseActivity;
import com.madroid.moxtraapp.R;

import butterknife.ButterKnife;

/**
 * Created by mohamed on 15/01/17.
 */
public class LoginActivity extends BaseActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_activity);
        ButterKnife.bind(this);
        startLoginFrgament();
    }

    private void startLoginFrgament() {
        LoginFragment loginFragment = new LoginFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.layCommonActivity,loginFragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
