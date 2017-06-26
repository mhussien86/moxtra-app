package com.madroid.moxtraapp.ui.categories_create;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.madroid.moxtraapp.R;


import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CreateCategoryActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_activity);
        unbinder = ButterKnife.bind(this);
        startCreateCategoryFragment();
    }

    private void startCreateCategoryFragment() {

        CreateCategoryFragment createCategoryFragment  = new CreateCategoryFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.layCommonActivity, createCategoryFragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
