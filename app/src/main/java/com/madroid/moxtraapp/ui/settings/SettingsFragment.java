package com.madroid.moxtraapp.ui.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.madroid.moxtraapp.AppConstants;
import com.madroid.moxtraapp.BaseActivity;
import com.madroid.moxtraapp.BaseFragment;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.LoginResponseDTO;
import com.moxtra.binder.ui.branding.widget.BrandingStateButton;
import com.moxtra.sdk.MXAccountManager;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import utils.PreferencesUtils;

/**
 * Created by mohamed on 25/02/17.
 */

public class SettingsFragment extends BaseFragment {


    @BindView(R.id.logout_button)
    BrandingStateButton logoutButton;

    @BindView(R.id.contact_name)
    TextView contactName;

    @BindView(R.id.contact_phone)
    TextView contactPhone;

    @BindView(R.id.contact_email)
    TextView contactEmail;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.seetings_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);

        Bundle b = getActivity().getIntent().getBundleExtra("bundle");
        LoginResponseDTO loginResponseDTO = Parcels.unwrap(b.getParcelable(AppConstants.LOGIN_RESPONSE));
        contactName.setText(loginResponseDTO.getResponse().getUserData().getFirstName() + loginResponseDTO.getResponse().getUserData().getLastName());
        contactEmail.setText(loginResponseDTO.getResponse().getUserData().getEmail());
        contactPhone.setText(loginResponseDTO.getResponse().getUserData().getPhone());

        return view;
    }


    @OnClick(R.id.logout_button)
    public void onLogoutClicked(View view) {
        Log.e("logout", "logout button clicked");
        Log.e("logout", "Moxtra account unlinked");
        Log.e("logout", "Sharedprefrences cleared");
        MXAccountManager.getInstance().unlinkAccount((BaseActivity) getActivity());
        PreferencesUtils.getInstance(getContext()).clearAllSharedPreferences(getContext());

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
