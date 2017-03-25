package com.madroid.moxtraapp.ui.categories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.madroid.moxtraapp.BaseFragment;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.LoginResponseDTO;
import com.madroid.moxtraapp.dtos.categories.AllCategoriesResponseDTO;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mohamed on 13/03/17.
 */

public class CategoriesFragment extends BaseFragment implements CategoriesView {

    @Bind(R.id.layout_loading)
    View loadingLayout ;

    CategoriesPresenter categoriesPresenter;
    LinearLayout layout;
    LayoutInflater inflater;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.categories_fragment, container, false);
        ButterKnife.bind(this,rootView);

        this.inflater = inflater ;
        LoginResponseDTO loginResponseDTO = Parcels.unwrap(getActivity().getIntent().getParcelableExtra("data"));
        categoriesPresenter = new CategoriesPresenterImpl(this);
        categoriesPresenter.getAllCategories(loginResponseDTO.getResponse().getAccessToken());
        layout = (LinearLayout) rootView.findViewById(R.id.layout);
        layout.setDividerDrawable(null);
        layout.setDividerPadding(30);
        return rootView;
    }

    @Override
    public void setAllCategories(AllCategoriesResponseDTO allCategories) {

        fillCategories(allCategories);

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

        Snackbar.make(getView(),""+message,Snackbar.LENGTH_LONG).show();
    }

    private void fillCategories(final AllCategoriesResponseDTO allCategories) {

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        View categoriesList = inflater.inflate(R.layout.categories_recycle_view, null);
        RecyclerView recycleView = (RecyclerView) categoriesList.findViewById(R.id.categories_recycler_view);
        recycleView.setLayoutManager(llm);
        recycleView.setHasFixedSize(true);

        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(allCategories.getData().getCategories(), getActivity(), new CategoriesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(AllCategoriesResponseDTO.Category category) {

            }
        });

        recycleView.setAdapter(categoriesAdapter);
        layout.addView(categoriesList);

    }
}
