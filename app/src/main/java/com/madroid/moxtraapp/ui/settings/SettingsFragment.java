package com.madroid.moxtraapp.ui.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.madroid.moxtraapp.BaseActivity;
import com.madroid.moxtraapp.BaseFragment;
import com.madroid.moxtraapp.R;
import com.moxtra.sdk.MXAccountManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mohamed on 25/02/17.
 */

public class SettingsFragment extends BaseFragment{


    @Bind(R.id.logout_button)
    Button logoutButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.seetings_fragment,container,false);
        ButterKnife.bind(this,view);
        return view;
    }



    @OnClick(R.id.logout_button)
    public void onLogoutClicked(View view){
        Log.e("logout", "logout button clicked");
        MXAccountManager.getInstance().unlinkAccount((BaseActivity)getActivity());

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
