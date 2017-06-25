package com.madroid.moxtraapp.ui.contactdetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import com.madroid.moxtraapp.BaseActivity;
import com.madroid.moxtraapp.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by mohamed on 25/02/17.
 */

public class ContactDetailsActivity extends BaseActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_activity);
        unbinder = ButterKnife.bind(this);
        startContactsDetailsFragment();

    }

    private void startContactsDetailsFragment() {

        ContactDetailsFragment contactDetailsFragment = new ContactDetailsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.layCommonActivity, contactDetailsFragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}