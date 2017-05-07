package com.madroid.moxtraapp.ui.meet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.madroid.moxtraapp.BaseFragment;
import com.madroid.moxtraapp.R;

import butterknife.ButterKnife;

/**
 * Created by mohamed on 08/05/17.
 */

public class JoinMeetingFragment extends BaseFragment{


    View view ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_join_meet, container, false);

        ButterKnife.bind(this,view);
//        setUpToolBar("Meetings");
        return view;
    }

}
