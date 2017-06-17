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
import com.madroid.moxtraapp.dtos.categories.AllCategoriesResponseDTO;
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
    @Bind(R.id.categories_list)
    RecyclerView categoriesRecyclerView;
    @Bind(R.id.recentPeopleHolder)
    View recentPeopleHolder;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    CategoriesPresenter categoriesPresenter;
    List<BindersResponseDTO.Binder> recentPeople;
    List<AllCategoriesResponseDTO.Category> categories;
    RecentPeopleAdapter mAdapter;
    CategoriesAdapter cAdapter;
    Map<Integer, List<BindersResponseDTO.Binder>> categoriesBindersMap;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.categories_fragment, container, false);
        ButterKnife.bind(this,rootView);
        setUpToolBar();
        Bundle b = getActivity().getIntent().getBundleExtra("bundle");
        LoginResponseDTO loginResponseDTO = Parcels.unwrap(b.getParcelable(AppConstants.LOGIN_RESPONSE));
        categoriesPresenter = new CategoriesPresenterImpl(this);
        categoriesPresenter.getCategoriesAndBinders(loginResponseDTO.getResponse().getAccessToken(),"all","feeds");
        return rootView;
    }


    @Override
    public void setCategoriesAndBinders(CategoriesAndBindersDTO categoriesAndBindersList) {

        List<BindersResponseDTO.Binder> binders = new ArrayList<>();
        binders = categoriesAndBindersList.bindersResponseDTO.getData().getBinders();
        categories = new ArrayList<>();
        categories = categoriesAndBindersList.allCategoriesResponseDTO.getData().getCategories();

        setRecentPeopleList(binders);
        setCategoriesWithBinders(binders, categories);
        mAdapter = new RecentPeopleAdapter(recentPeople, getContext());
        cAdapter = new CategoriesAdapter(categories, categoriesBindersMap, getContext());
        recentPeopleRecyclerView.setAdapter(mAdapter);
        categoriesRecyclerView.setAdapter(cAdapter);
        recentPeopleHolder.setVisibility(View.VISIBLE);

    }


    void setRecentPeopleList(List<BindersResponseDTO.Binder> binders){
        recentPeople = new ArrayList<>();
        Iterator<BindersResponseDTO.Binder> it = binders.iterator();
        while (it.hasNext()) {
            BindersResponseDTO.Binder binder = it.next();
            if (binder.getSubBinder().getUsers().size() > 1 && binder.getSubBinder().getConversation() == true) {
                recentPeople.add(binder);
            }
        }
    }

    void setCategoriesWithBinders(List<BindersResponseDTO.Binder> binders, List<AllCategoriesResponseDTO.Category> categories){

        List<BindersResponseDTO.Binder> tempBinder = new ArrayList<>();

        Iterator<BindersResponseDTO.Binder> binderIt= binders.iterator();
        Iterator<AllCategoriesResponseDTO.Category> categoriesIt = categories.iterator();

        while (categoriesIt.hasNext()) {
            AllCategoriesResponseDTO.Category category = categoriesIt.next();
            int key = category.getId();
            while (binderIt.hasNext()) {
                BindersResponseDTO.Binder binder = binderIt.next();
                if(binder.getCategory() == key && !binder.getSubBinder().getConversation()){
                    tempBinder.add(binder);
                }
            }
        }


        categoriesBindersMap = new HashMap<Integer, List<BindersResponseDTO.Binder>>();

        for (BindersResponseDTO.Binder binder : tempBinder) {
            int key  = binder.getCategory();
            if(categoriesBindersMap.containsKey(key)){
                List<BindersResponseDTO.Binder> list = categoriesBindersMap.get(key);
                list.add(binder);
            }else{
                List<BindersResponseDTO.Binder> list = new ArrayList<BindersResponseDTO.Binder>();
                list.add(binder);
                categoriesBindersMap.put(key, list);
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
