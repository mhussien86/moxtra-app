package com.madroid.moxtraapp.ui.categories_binders_add;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.madroid.moxtraapp.BaseFragment;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.binders.BindersResponseDTO;
import com.madroid.moxtraapp.dtos.categories.BindersAddCategoryRequestDTO;
import com.madroid.moxtraapp.dtos.simple.SimpleResponseDTO;
import com.madroid.moxtraapp.ui.DataHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class BindersAddFragment extends BaseFragment implements BindersAddView {

    @BindView(R.id.layout_loading)
    View loadingLayout;
    @BindView(R.id.binders_add_list)
    RecyclerView bindersAddList;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    List<BindersResponseDTO.Binder> binders = new ArrayList<>();
    BindersAddAdapter mAdapter;
    private Unbinder unbinder;
    BindersAddPresenter bindersAddPresenter;
    Integer categoryId;

    public BindersAddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.binders_add_fragment, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        setUpToolBar();
        BindersAddActivity bindersAddActivity = (BindersAddActivity) getActivity();
        binders = bindersAddActivity.getBinders();
        categoryId = bindersAddActivity.getCategoryId();
        mAdapter = new BindersAddAdapter(binders, getContext());
        LinearLayoutManager mLayoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(bindersAddList.getContext(),
                mLayoutManager.getOrientation());
        bindersAddList.addItemDecoration(mDividerItemDecoration);
        bindersAddList.setAdapter(mAdapter);
        bindersAddPresenter = new BindersAddPresenterImpl(this);
        return rootView;
    }

    private void setUpToolBar() {
        //add the Toolbar

        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.binders_add_toolbar, null);
        toolbar.addView(mCustomView);
        TextView add = (TextView) toolbar.findViewById(R.id.complete);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("oo", "complete clciked");
                createBindersAddCategoryBody();

                bindersAddPresenter.assignBindersToCategory(categoryId, DataHolder.getInstance().getToken(), createBindersAddCategoryBody());
            }
        });
        ((BindersAddActivity) getActivity()).setSupportActionBar(toolbar);
        ((BindersAddActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((BindersAddActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    private BindersAddCategoryRequestDTO createBindersAddCategoryBody() {

        BindersAddCategoryRequestDTO bindersAddCategoryBody = new BindersAddCategoryRequestDTO();
        bindersAddCategoryBody.setBinders(new ArrayList<BindersAddCategoryRequestDTO.Binder>());
        if (mAdapter.getSelectedBinders().size() > 0) {
            for (BindersResponseDTO.Binder selectedBinders : mAdapter.getSelectedBinders()) {
                BindersAddCategoryRequestDTO.Binder_ binder_ = bindersAddCategoryBody.new Binder_(selectedBinders.getSubBinder().getId());
                BindersAddCategoryRequestDTO.Binder binder = bindersAddCategoryBody.new Binder(binder_);
                bindersAddCategoryBody.getBinders().add(binder);
            }
        }
        return bindersAddCategoryBody;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void setAssignBindersToCategory(SimpleResponseDTO responseDTO) {
        Intent intent = new Intent();
        intent.putExtra("onResume", true);
        getActivity().setResult(RESULT_OK, intent);
        getActivity().finish();

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
}
