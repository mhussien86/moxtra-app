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

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener{


    BottomNavigationView bottomNavigationView;

    ButterKnife binder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);



    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.e("Hello ", "Favorite clicked");
        switch (item.getItemId()) {
            case R.id.action_favorites:
                BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
                ContactsListFragment contactsListFragment = new ContactsListFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container,contactsListFragment).commit();
                break;
            case R.id.action_schedules:
                BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
                break;
            case R.id.action_music:
                BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
                break;
        }
        return true;
    }
}
