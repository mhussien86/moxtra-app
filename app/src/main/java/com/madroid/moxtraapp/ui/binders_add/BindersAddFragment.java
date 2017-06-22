package com.madroid.moxtraapp.ui.binders_add;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.binders.BindersResponseDTO;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BindersAddFragment extends Fragment {

    @Bind(R.id.binders_add_list)
    RecyclerView bindersAddList;
    List<BindersResponseDTO.Binder> binders = new ArrayList<>();
    BindersAddAdapter mAdapter;

    public BindersAddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.binders_add_fragment, container, false);
        ButterKnife.bind(this,rootView);
        BindersAddActivity bindersAddActivity = (BindersAddActivity) getActivity();
        binders = bindersAddActivity.getBinders();
        mAdapter = new BindersAddAdapter(binders, getContext());
        LinearLayoutManager mLayoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(bindersAddList.getContext(),
                mLayoutManager.getOrientation());
        bindersAddList.addItemDecoration(mDividerItemDecoration);
        bindersAddList.setAdapter(mAdapter);
        return rootView;
    }

}
