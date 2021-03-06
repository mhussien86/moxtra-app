package com.madroid.moxtraapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import com.madroid.moxtraapp.BaseActivity;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.ui.categories.CategoriesFragment;
import com.madroid.moxtraapp.ui.contactslist.ContactsListFragment;
import com.madroid.moxtraapp.ui.helper.BottomNavigationViewHelper;
import com.madroid.moxtraapp.ui.meet.MeetingsFragment;
import com.madroid.moxtraapp.ui.settings.SettingsFragment;
import com.madroid.moxtraapp.ui.timeline.TimelineListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    private Unbinder unbinder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        startTimelineFragment();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_timeline:
                BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
                startTimelineFragment();
                break;
            case R.id.action_meet:
                BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
                startMeetFragment();
                break;
            case R.id.action_contacts:
                BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
                startContactsFragment();
                break;
            case R.id.action_categories:
                BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
                startCategoriesFragment();
                break;
            case R.id.action_settings:
                BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
                startSettingsFragment();
                break;
        }
        return true;
    }


    void startContactsFragment() {
        ContactsListFragment contactsListFragment = new ContactsListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, contactsListFragment).commit();
    }


    void startTimelineFragment() {

        TimelineListFragment timelineListFragment = new TimelineListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, timelineListFragment).commit();
    }

    void startMeetFragment() {

//        MeetNowFragment joinMeetingFragment = new MeetNowFragment();
        MeetingsFragment meetingsFragment = new MeetingsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, meetingsFragment).commit();

    }

    void startCategoriesFragment() {

        CategoriesFragment categoriesFragment = new CategoriesFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, categoriesFragment).commit();
    }


    void startSettingsFragment() {
        SettingsFragment settingsFragment = new SettingsFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, settingsFragment).commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
