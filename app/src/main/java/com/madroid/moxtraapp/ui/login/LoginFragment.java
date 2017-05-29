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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.madroid.moxtraapp.AppConstants;
import com.madroid.moxtraapp.BaseFragment;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.LoginRequestDTO;
import com.madroid.moxtraapp.dtos.LoginResponseDTO;
import com.madroid.moxtraapp.ui.MainActivity;
import com.moxtra.sdk.MXAccountManager;
import com.moxtra.sdk.MXSDKConfig;

import org.parceler.Parcels;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.PreferencesUtils;

/**
 * Created by mohamed on 15/01/17.
 */
public class LoginFragment extends BaseFragment implements LoginView{

    LoginPresenter loginPresenter ;
    @Bind(R.id.username)
    EditText emailEditText;

    @Bind(R.id.password)
    EditText passwordEditText;

    @Bind(R.id.layout_loading)
    View loadingLayout ;

    @Bind(R.id.login_button)
    Button loginButton ;

    @Bind(R.id.checkBox)
    CheckBox checkBox ;

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

    @OnClick({R.id.login_button, R.id.checkBox})
    protected void onLoginButtonClicked(View view){

        if(view.getId()==R.id.login_button) {
            if (TextUtils.isEmpty(emailEditText.getText())) {
                emailEditText.setError("Please enter your email address");
            } else if (TextUtils.isEmpty(passwordEditText.getText())) {
                passwordEditText.setError("Please enter your password");
            } else {
                loginPresenter.login(new LoginRequestDTO(emailEditText.getText().toString(), passwordEditText.getText().toString()));
            }
        }else if (view.getId()==R.id.checkBox){


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

    private String regid;

    @Override
    public void succededToLogin(final LoginResponseDTO loginResponseDTO) {



//        try {
//            MXAccountManager.createInstance(getActivity(), loginResponseDTO.getResponse().getClientId(), loginResponseDTO.getResponse().clientSecret, true);
//            Log.e(TAG,  MXAccountManager.getInstance().getMyToken()+ MXAccountManager.getInstance().getMyUserID());
            Log.e(TAG,  "Logged in and integrated with the client id and client secret new");
            if(checkBox.isChecked()){
                saveUserData(emailEditText.getText().toString(), passwordEditText.getText().toString());
            }

//        } catch (MXSDKException.InvalidParameter invalidParameter) {
//            Log.e(TAG, "Error when creating MXAccountManager instance.", invalidParameter);
//        }

            try {
                LoginResponseDTO.UserData user = loginResponseDTO.getResponse().getUserData();
                Bitmap avatar = BitmapFactory.decodeStream(getActivity().getAssets().open("A01.png"));
                final MXSDKConfig.MXUserInfo mxUserInfo = new MXSDKConfig.MXUserInfo(loginResponseDTO.getResponse().accessToken, MXSDKConfig.MXUserIdentityType.IdentityTypeSSOAccessToken);
                final MXSDKConfig.MXProfileInfo mxProfileInfo = new MXSDKConfig.MXProfileInfo(user.firstName, user.lastName, avatar);
                showProgress();

                MXAccountManager.getInstance().setupUser(mxUserInfo, mxProfileInfo, user.getMoxtraOrgId(), null, new MXAccountManager.MXAccountLinkListener() {
                    @Override
                    public void onLinkAccountDone(boolean success) {
                        if (success) {
                            hideProgress();
                            Log.i(TAG, "Linked to moxtra account successfully.");
                            Log.e("Accecss token", loginResponseDTO.getResponse().getAccessToken());

                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            Bundle b = new Bundle();
                            b.putParcelable(AppConstants.LOGIN_RESPONSE, Parcels.wrap(loginResponseDTO));
                            intent.putExtra("bundle", b);
                            startActivity(intent);
                            getActivity().finish();
                        } else {
                            hideProgress();

                            // When the user is already linked ?
                            // Call back
                            //

                            Toast.makeText(getActivity(), "Failed to setup moxtra user.", Toast.LENGTH_LONG).show();
                            Log.e(TAG, "Failed to setup moxtra user.");
                        }
                    }
                });
            } catch (IOException e) {
                Log.e(TAG, "Can't decode avatar.", e);
            }

    }

    private void saveUserData(String email, String password) {

        PreferencesUtils preferencesUtils = PreferencesUtils.getInstance(getContext());
        preferencesUtils.setBoolean(AppConstants.IS_LOGGED_IN,true);
        preferencesUtils.setString(AppConstants.USER_EMAIL,email);
        preferencesUtils.setString(AppConstants.USER_PASSWORD,password);

    }




}
