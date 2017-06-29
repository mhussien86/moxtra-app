package com.madroid.moxtraapp.ui.categories_manage;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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
import com.madroid.moxtraapp.dtos.simple.SimpleResponseDTO;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManageCategoriesFragment extends BaseFragment implements ManageCategoriesView {

    @BindView(R.id.manage_categories_list)
    RecyclerView manageCategoriesList;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.layout_loading)
    View loadingLayout;
    private List<AllCategoriesResponseDTO.Category> categories = new ArrayList<>();
    private Unbinder unbinder;
    private ManageCategoriesAdapter mAdapter;
    private boolean actionDone;
    public ManageCategoriesPresenter manageCategoriesPresenter;

    public ManageCategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.manage_categories_fragment, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        ManageCategoriesActivity manageCategoriesActivity = (ManageCategoriesActivity) getActivity();
        categories = manageCategoriesActivity.getCategories();
        mAdapter = new ManageCategoriesAdapter(categories, getContext(), ManageCategoriesFragment.this);
        LinearLayoutManager mLayoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(manageCategoriesList.getContext(),
                mLayoutManager.getOrientation());
        manageCategoriesList.addItemDecoration(mDividerItemDecoration);
        manageCategoriesList.setAdapter(mAdapter);
        manageCategoriesPresenter = new ManageCategoryPresenterImpl(this);
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

                finishActivity();
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

    @Override
    public void setRenameCategory(SimpleResponseDTO responseDTO) {

        if (categories.contains(mAdapter.getSelectedCategory())) {
            categories.get(categories.indexOf(mAdapter.getSelectedCategory())).setName(mAdapter.getNewCategoryName());
        }
        actionDone = true;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setDeleteCategory(SimpleResponseDTO responseDTO) {
        categories.remove(mAdapter.getSelectedCategory());
        actionDone = true;
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        loadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loadingLayout.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Snackbar.make(getView(), "" + message, Snackbar.LENGTH_LONG).show();
    }

    private void finishActivity() {
        Intent intent = new Intent();
        if (actionDone) {
            intent.putExtra("onResume", true);
            getActivity().setResult(RESULT_OK, intent);
        }
        getActivity().finish();
    }
}
