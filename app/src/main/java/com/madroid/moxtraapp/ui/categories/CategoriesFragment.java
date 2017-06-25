package com.madroid.moxtraapp.ui.categories;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.madroid.moxtraapp.AppConstants;
import com.madroid.moxtraapp.BaseActivity;
import com.madroid.moxtraapp.BaseFragment;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.LoginResponseDTO;
import com.madroid.moxtraapp.dtos.binders.BindersResponseDTO;
import com.madroid.moxtraapp.dtos.categories.AllCategoriesResponseDTO;
import com.madroid.moxtraapp.dtos.catergoriesandbinders.CategoriesAndBindersDTO;
import com.madroid.moxtraapp.ui.DataHolder;
import com.madroid.moxtraapp.ui.meet.MeetingsContainerActivity;
import com.madroid.moxtraapp.ui.timeline.ContactsListActivity;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by mohamed on 13/03/17.
 */

public class CategoriesFragment extends BaseFragment implements CategoriesView {

    @BindView(R.id.layout_loading)
    View loadingLayout ;
    @BindView(R.id.recent_people_list)
    RecyclerView recentPeopleRecyclerView;
    @BindView(R.id.categories_list)
    RecyclerView categoriesRecyclerView;
    @BindView(R.id.recentPeopleHolder)
    View recentPeopleHolder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    CategoriesPresenter categoriesPresenter;
    List<BindersResponseDTO.Binder> recentPeople;
    List<AllCategoriesResponseDTO.Category> categories;
    RecentPeopleAdapter mAdapter;
    CategoriesAdapter cAdapter;
    Map<Integer, List<BindersResponseDTO.Binder>> categoriesBindersMap;
    private Unbinder unbinder;
    boolean shouldExecuteOnResume;
    CategoriesFragment categoriesFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.categories_fragment, container, false);
        unbinder = ButterKnife.bind(this,rootView);
        setUpToolBar();
        Bundle b = getActivity().getIntent().getBundleExtra("bundle");
        LoginResponseDTO loginResponseDTO = Parcels.unwrap(b.getParcelable(AppConstants.LOGIN_RESPONSE));
        categoriesPresenter = new CategoriesPresenterImpl(this);
        categoriesPresenter.getCategoriesAndBinders(loginResponseDTO.getResponse().getAccessToken(),"all","feeds");
        shouldExecuteOnResume = false;
        categoriesFragment = this;
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
        cAdapter = new CategoriesAdapter(categories, categoriesBindersMap, binders, getContext(), categoriesFragment);
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
        ImageView add = (ImageView) toolbar.findViewById(R.id.icon_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showMenuPopup(view, R.menu.popup_new_action);
            }
        });
        ((BaseActivity)getActivity()).setSupportActionBar(toolbar);
        ((BaseActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((BaseActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    private void showMenuPopup(View v, int resource) {
        PopupMenu popup = new PopupMenu(getActivity(), v);
        // Inflate the menu from xml
        popup.getMenuInflater().inflate(resource, popup.getMenu());


            // Setup menu item selection
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu_meet_now:
                            getActivity().startActivity(new Intent(new Intent(getActivity(), MeetingsContainerActivity.class)).putExtra("data","start"));
                            return true;
                        case R.id.menu_group_conversation:
                            Toast.makeText(getActivity(), "menu_group_conversation!", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.menu_direct_message:
                            try {
                                Bundle b = getActivity().getIntent().getBundleExtra("bundle");
                                LoginResponseDTO loginResponseDTO = Parcels.unwrap(b.getParcelable(AppConstants.LOGIN_RESPONSE));
                                Intent intent = new Intent(getActivity(), ContactsListActivity.class);
                                b.putParcelable(AppConstants.LOGIN_RESPONSE, Parcels.wrap(loginResponseDTO));
                                intent.putExtra("bundle", b);
                                startActivity(intent);
                            }catch(Exception e){

                            }
                            return true;
                        default:
                            return false;
                    }
                }
            });



        MenuPopupHelper menuHelper = new MenuPopupHelper(getActivity(), (MenuBuilder) popup.getMenu(), v);
        menuHelper.setForceShowIcon(true);
        menuHelper.setGravity(Gravity.CENTER);
        menuHelper.show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (0) : {
                if (resultCode == Activity.RESULT_OK) {
                    shouldExecuteOnResume = data.getBooleanExtra("onResume", false);
                }
                break;
            }
        }
    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        if(shouldExecuteOnResume) {
            categoriesPresenter.getCategoriesAndBinders(DataHolder.getInstance().getToken(), "all", "feeds");
            shouldExecuteOnResume = false;
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
