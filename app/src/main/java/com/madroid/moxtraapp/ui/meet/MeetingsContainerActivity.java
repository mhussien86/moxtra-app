package com.madroid.moxtraapp.ui.meet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import com.madroid.moxtraapp.BaseActivity;
import com.madroid.moxtraapp.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by mohamed on 14/05/17.
 */

public class MeetingsContainerActivity extends BaseActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_activity);
        unbinder = ButterKnife.bind(this);

        if (getIntent().getStringExtra("data").equalsIgnoreCase("start")) {
            startMeetNowFragment();
        } else if (getIntent().getStringExtra("data").equalsIgnoreCase("join")) {
            startJoinMeetFragment();
        }
    }

//    private void startContactsDetailsFragment() {
//
//        ContactDetailsFragment contactDetailsFragment = new ContactDetailsFragment();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.layCommonActivity, contactDetailsFragment).commit();
//    }

    private void startMeetNowFragment() {

        MeetNowFragment meetNowFragment = new MeetNowFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.layCommonActivity, meetNowFragment).commit();
    }


    private void startJoinMeetFragment() {

        JoinMeetFragment joinMeetFragment = new JoinMeetFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.layCommonActivity, joinMeetFragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
