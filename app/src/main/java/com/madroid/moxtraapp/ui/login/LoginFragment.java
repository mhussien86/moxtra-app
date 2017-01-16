package com.madroid.moxtraapp.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.madroid.moxtraapp.BaseFragment;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.LoginResponseDTO;

import butterknife.Bind;

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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_login,container,false);
        loginPresenter = new LoginPresenterImpl(this);

        return rootView;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

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

    @Override
    public void succededToLogin(LoginResponseDTO loginResponseDTO) {

        Snackbar.make(getView(),""+loginResponseDTO.getUserData().getFirstName(),Snackbar.LENGTH_LONG).show();



    }
}
