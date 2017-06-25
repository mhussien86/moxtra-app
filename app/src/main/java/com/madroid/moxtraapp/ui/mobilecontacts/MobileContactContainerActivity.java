package com.madroid.moxtraapp.ui.mobilecontacts;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.madroid.moxtraapp.BaseActivity;
import com.madroid.moxtraapp.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MobileContactContainerActivity extends BaseActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_activity);
        unbinder = ButterKnife.bind(this);
        startMobileContactsFragment();
    }

    private void startMobileContactsFragment() {

        MobileContactsFragment mobileContactsFragment = new MobileContactsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.layCommonActivity, mobileContactsFragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
