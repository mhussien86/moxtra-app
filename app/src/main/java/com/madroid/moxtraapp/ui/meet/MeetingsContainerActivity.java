package com.madroid.moxtraapp.ui.meet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import com.madroid.moxtraapp.BaseActivity;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.ui.contactdetails.ContactDetailsFragment;

import butterknife.ButterKnife;

/**
 * Created by mohamed on 14/05/17.
 */

public class MeetingsContainerActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_activity);
        ButterKnife.bind(this);
        startJoinMeetFragment();

    }

    private void startContactsDetailsFragment() {

        ContactDetailsFragment contactDetailsFragment = new ContactDetailsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.layCommonActivity, contactDetailsFragment).commit();
    }

    private void startJoinMeetFragment() {

        MeetNowFragment meetNowFragment = new MeetNowFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.layCommonActivity, meetNowFragment).commit();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
