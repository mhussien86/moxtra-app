package com.madroid.moxtraapp.ui.categories_manage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.categories.AllCategoriesResponseDTO;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ManageCategoriesActivity extends AppCompatActivity {

    private List<AllCategoriesResponseDTO.Category> categories = new ArrayList<>();
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_activity);
        unbinder = ButterKnife.bind(this);
        Intent i = getIntent();
        categories = (List<AllCategoriesResponseDTO.Category>) i.getSerializableExtra("categories");
        startCreateCategoryFragment();
    }

    private void startCreateCategoryFragment() {

        ManageCategoriesFragment manageCategoriesFragment = new ManageCategoriesFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.layCommonActivity, manageCategoriesFragment).commit();
    }

    public List<AllCategoriesResponseDTO.Category> getCategories() {
        return categories;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
