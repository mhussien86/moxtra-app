package com.madroid.moxtraapp.ui.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.madroid.moxtraapp.BaseFragment;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.LoginRequestDTO;
import com.madroid.moxtraapp.dtos.LoginResponseDTO;
import com.madroid.moxtraapp.ui.contactslist.ContactsListActivity;
import com.moxtra.sdk.MXAccountManager;
import com.moxtra.sdk.MXSDKConfig;
import com.moxtra.sdk.MXSDKException;

import org.parceler.Parcels;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mohamed on 15/01/17.
 */
public class LoginFragment extends BaseFragment implements LoginView, MXAccountManager.MXAccountLinkListener {

    LoginPresenter loginPresenter ;
    @Bind(R.id.username)
    EditText emailEditText;

    @Bind(R.id.password)
    EditText passwordEditText;

    @Bind(R.id.layout_loading)
    View loadingLayout ;

    @Bind(R.id.login_button)
    Button loginButton ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_login,container,false);
        ButterKnife.bind(this,rootView);

        loginPresenter = new LoginPresenterImpl(this);

        return rootView;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @OnClick(R.id.login_button)
    protected void onLoginButtonClicked(View view){

        if(TextUtils.isEmpty(emailEditText.getText())){
            emailEditText.setError("Please enter your email address");
        }else if(TextUtils.isEmpty(passwordEditText.getText())){
            passwordEditText.setError("Please enter your password");
        }else {
            loginPresenter.login(new LoginRequestDTO(emailEditText.getText().toString(),passwordEditText.getText().toString()));
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

    private static final String TAG = "MoxieChatApplication";

    @Override
    public void succededToLogin(LoginResponseDTO loginResponseDTO) {

        try {
            MXAccountManager.createInstance(getActivity(), loginResponseDTO.getResponse().getClientId(), loginResponseDTO.getResponse().clientSecret, true);
            Log.e(TAG,  MXAccountManager.getInstance().getMyToken()+ MXAccountManager.getInstance().getMyUserID());
            Log.e(TAG,  "Logged in and integrated with the client id and client secret new");

        } catch (MXSDKException.InvalidParameter invalidParameter) {
            Log.e(TAG, "Error when creating MXAccountManager instance.", invalidParameter);
        }

        try {

            LoginResponseDTO.UserData user = loginResponseDTO.getResponse().getUserData();
            Bitmap avatar = BitmapFactory.decodeStream(getActivity().getAssets().open("A01.png"));
            final MXSDKConfig.MXUserInfo mxUserInfo = new MXSDKConfig.MXUserInfo(user.email, MXSDKConfig.MXUserIdentityType.IdentityUniqueId);
            final MXSDKConfig.MXProfileInfo mxProfileInfo = new MXSDKConfig.MXProfileInfo(user.firstName, user.lastName,avatar);
            MXAccountManager.getInstance().setupUser(mxUserInfo, mxProfileInfo, null, null, this);
        } catch (IOException e) {
            Log.e(TAG, "Can't decode avatar.", e);
        }
        Intent intent = new Intent(getActivity(), ContactsListActivity.class);
        intent.putExtra("data", Parcels.wrap(loginResponseDTO));
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onLinkAccountDone(boolean success) {


        if (success) {
            Log.i(TAG, "Linked to moxtra account successfully.");
//            startChatListActivity();
        } else {
            Toast.makeText(getActivity(), "Failed to setup moxtra user.", Toast.LENGTH_LONG).show();
            Log.e(TAG, "Failed to setup moxtra user.");
//            showProgress(false);
        }
    }
}
