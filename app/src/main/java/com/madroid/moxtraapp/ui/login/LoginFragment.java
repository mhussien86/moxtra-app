package com.madroid.moxtraapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.madroid.moxtraapp.BaseFragment;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.LoginRequestDTO;
import com.madroid.moxtraapp.dtos.LoginResponseDTO;
import com.madroid.moxtraapp.ui.contactslist.ContactsListActivity;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    public void succededToLogin(LoginResponseDTO loginResponseDTO) {

        Intent intent = new Intent(getActivity(), ContactsListActivity.class);
        intent.putExtra("data", Parcels.wrap(loginResponseDTO));
        startActivity(intent);
        getActivity().finish();
    }
}
