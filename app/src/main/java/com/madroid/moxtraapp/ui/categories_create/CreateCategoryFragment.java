package com.madroid.moxtraapp.ui.categories_create;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.madroid.moxtraapp.BaseFragment;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.simple.SimpleResponseDTO;
import com.madroid.moxtraapp.ui.DataHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateCategoryFragment extends BaseFragment implements CreateCategoryView {

    @BindView(R.id.layout_loading)
    View loadingLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private Unbinder unbinder;
    CreateCategoryPresenter createCategoryPresenter;
    @BindView(R.id.category_name_et)
    EditText categoryNameEditText;

    public CreateCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.create_category_fragment, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        createCategoryPresenter = new CreateCategoryPresenterImpl(this);
        setUpToolBar();
        return rootView;
    }

    private void setUpToolBar() {
        //add the Toolbar

        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.create_category_toolbar, null);
        toolbar.addView(mCustomView);
        TextView add = (TextView) toolbar.findViewById(R.id.next);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("oo", "complete clciked");
                String category_name = categoryNameEditText.getText().toString();
                if (category_name != null && !category_name.isEmpty()) {
                    JsonObject json = new JsonObject();
                    json.addProperty("name", category_name);
                    createCategoryPresenter.createCategory(DataHolder.getInstance().getToken(), json);
                }
            }
        });
        ((CreateCategoryActivity) getActivity()).setSupportActionBar(toolbar);
        ((CreateCategoryActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((CreateCategoryActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        createCategoryPresenter.unsubscribe();
    }

    @Override
    public void setCreateCategory(SimpleResponseDTO responseDTO) {
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
