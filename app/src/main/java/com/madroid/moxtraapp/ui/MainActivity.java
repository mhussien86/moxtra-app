package com.madroid.moxtraapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.MenuItem;

import com.madroid.moxtraapp.BaseActivity;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.ui.contactslist.ContactsListFragment;
import com.madroid.moxtraapp.ui.helper.BottomNavigationViewHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener{


    @Bind(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    ButterKnife binder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binder.bind(this);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.e("Hello ", "Favorite clicked");
        switch (item.getItemId()) {
            case R.id.action_timeline:
                BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
                startContactsFragment();
                break;
            case R.id.action_meet:
                BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
                break;
            case R.id.action_contacts:
                BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
                break;
            case R.id.action_categories:
                BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
                break;
            case R.id.action_settings:
                BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
                break;
        }
        return true;
    }


    void startContactsFragment(){
        ContactsListFragment contactsListFragment = new ContactsListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container,contactsListFragment).commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        binder.unbind(this);
    }
}
