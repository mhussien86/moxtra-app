package com.madroid.moxtraapp.ui.categories_manage;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.madroid.moxtraapp.BaseFragment;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.categories.AllCategoriesResponseDTO;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManageCategoriesFragment extends BaseFragment {

    @BindView(R.id.manage_categories_list)
    RecyclerView manageCategoriesList;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private List<AllCategoriesResponseDTO.Category> categories = new ArrayList<>();
    private Unbinder unbinder;
    private ManageCategoriesAdapter mAdapter;

    public ManageCategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.manage_categories_fragment, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        ManageCategoriesActivity manageCategoriesActivity = (ManageCategoriesActivity) getActivity();
        categories = manageCategoriesActivity.getCategories();
        mAdapter = new ManageCategoriesAdapter(categories, getContext());
        LinearLayoutManager mLayoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(manageCategoriesList.getContext(),
                mLayoutManager.getOrientation());
        manageCategoriesList.addItemDecoration(mDividerItemDecoration);
        manageCategoriesList.setAdapter(mAdapter);
        setUpToolBar();
        return rootView;
    }

    private void setUpToolBar() {
        //add the Toolbar

        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.manage_category_toolbar, null);
        toolbar.addView(mCustomView);
        TextView close = (TextView) toolbar.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().finish();
            }
        });
        ((ManageCategoriesActivity) getActivity()).setSupportActionBar(toolbar);
        ((ManageCategoriesActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((ManageCategoriesActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
