package com.madroid.moxtraapp.ui.timeline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import com.madroid.moxtraapp.BaseActivity;
import com.madroid.moxtraapp.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by mohamed on 16/01/17.
 */
public class ContactsListActivity extends BaseActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_activity);
        unbinder = ButterKnife.bind(this);
        startContactsListFragment();

    }

    private void startContactsListFragment() {

        ContactsListFragment contactsListFragment = new ContactsListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.layCommonActivity, contactsListFragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
