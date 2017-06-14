package com.madroid.moxtraapp.ui.intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.madroid.moxtraapp.BaseFragment;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.ui.login.LoginActivity;
import com.viewpagerindicator.CirclePageIndicator;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mohamed on 25/03/17.
 */

public class AppIntroFragment extends BaseFragment {


    View view ;
    @Bind(R.id.intro_viewpager)
    ViewPager _mViewPager;
    @Bind(R.id.indicator)
    CirclePageIndicator mIndicator;
    private IntroViewPagerAdapter _adapter;

    @Bind(R.id.intro_login_btn)
    ImageButton introLogin;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.app_intro_fragment, container, false);
        ButterKnife.bind(this,view);
        setUpView();
        return view;
    }

    private void setUpView(){
        _adapter = new IntroViewPagerAdapter(getActivity().getApplicationContext(),getChildFragmentManager());
        _mViewPager.setAdapter(_adapter);
        _mViewPager.setCurrentItem(0);
//        mIndicator.setf(false);
        mIndicator.setViewPager(_mViewPager);

        introLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
