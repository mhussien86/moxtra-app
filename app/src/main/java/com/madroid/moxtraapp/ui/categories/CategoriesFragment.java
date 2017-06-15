package com.madroid.moxtraapp.ui.categories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.madroid.moxtraapp.AppConstants;
import com.madroid.moxtraapp.BaseActivity;
import com.madroid.moxtraapp.BaseFragment;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.LoginResponseDTO;
import com.madroid.moxtraapp.dtos.binders.BindersResponseDTO;
import com.madroid.moxtraapp.dtos.catergoriesandbinders.CategoriesAndBindersDTO;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mohamed on 13/03/17.
 */

public class CategoriesFragment extends BaseFragment implements CategoriesView {

    @Bind(R.id.layout_loading)
    View loadingLayout ;
    @Bind(R.id.recent_people_list)
    RecyclerView recentPeopleRecyclerView;
    @Bind(R.id.recentPeopleHolder)
    View recentPeopleHolder;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    CategoriesPresenter categoriesPresenter;
    List<BindersResponseDTO.Binder> recentPeople;
    RecentPeopleAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);

        View rootView = inflater.inflate(R.layout.categories_fragment, container, false);
        ButterKnife.bind(this,rootView);
        setUpToolBar();
        Bundle b = getActivity().getIntent().getBundleExtra("bundle");
        LoginResponseDTO loginResponseDTO = Parcels.unwrap(b.getParcelable(AppConstants.LOGIN_RESPONSE));
        categoriesPresenter = new CategoriesPresenterImpl(this);
        categoriesPresenter.getCategoriesAndBinders(loginResponseDTO.getResponse().getAccessToken(),"chat","feeds");
        return rootView;
    }


    @Override
    public void setCategoriesAndBinders(CategoriesAndBindersDTO categoriesAndBindersList) {

        Map<Integer, List<BindersResponseDTO.Binder>> map = new HashMap<Integer, List<BindersResponseDTO.Binder>>();

        for (BindersResponseDTO.Binder binder : categoriesAndBindersList.bindersResponseDTO.getData().getBinders()) {
            int key  = binder.getCategory();
            if(map.containsKey(key)){
                List<BindersResponseDTO.Binder> list = map.get(key);
                list.add(binder);

            }else{
                List<BindersResponseDTO.Binder> list = new ArrayList<BindersResponseDTO.Binder>();
                list.add(binder);
                map.put(key, list);
            }

        }

        setRecentPeopleList(categoriesAndBindersList.bindersResponseDTO.getData().getBinders());
        mAdapter = new RecentPeopleAdapter(recentPeople, getContext());
        recentPeopleRecyclerView.setAdapter(mAdapter);
        recentPeopleHolder.setVisibility(View.VISIBLE);

    }


    void setRecentPeopleList(List<BindersResponseDTO.Binder> binders){
        recentPeople = new ArrayList<>();
        Iterator<BindersResponseDTO.Binder> it = binders.iterator();
        while (it.hasNext()) {
            BindersResponseDTO.Binder binder = it.next();
            if (binder.getSubBinder().getUsers().size() > 1) {
                recentPeople.add(binder);
            }
        }
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

    private void setUpToolBar(){
        //add the Toolbar

        LayoutInflater mInflater=LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.categories_toolbar, null);
        toolbar.addView(mCustomView);
        ((BaseActivity)getActivity()).setSupportActionBar(toolbar);
        ((BaseActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((BaseActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
}
