package com.madroid.moxtraapp.ui.binders_add;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.binders.BindersResponseDTO;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class BindersAddActivity extends AppCompatActivity {

    private  List<BindersResponseDTO.Binder> binders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_activity);
        ButterKnife.bind(this);
        Intent i = getIntent();
        binders = (List<BindersResponseDTO.Binder>)i.getSerializableExtra("binders");
        startBindersAddFragment();
    }

    private void startBindersAddFragment() {

        BindersAddFragment bindersAddFragment = new BindersAddFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.layCommonActivity, bindersAddFragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public List<BindersResponseDTO.Binder> getBinders(){
        return binders;
    }
}
