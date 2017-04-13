package com.madroid.moxtraapp.ui.login;

import com.madroid.moxtraapp.dtos.LoginRequestDTO;
import com.madroid.moxtraapp.dtos.LoginResponseDTO;
import com.madroid.moxtraapp.interactors.logininteractors.LoginInteractor;
import com.madroid.moxtraapp.interactors.logininteractors.LoginInteractorImpl;

/**
 * Created by mohamed on 15/01/17.
 */
public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinished{

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        loginInteractor = new LoginInteractorImpl();

    }


    @Override
    public void login(LoginRequestDTO loginRequestDTO) {


        loginView.showProgress();

        loginInteractor.login(loginRequestDTO, this);

    }

    @Override
    public void onDestroy() {


        loginView.hideProgress();
        loginInteractor.unsubscribe();

    }

    @Override
    public void onLoginSucceed(LoginResponseDTO loginResponseDTO) {

        loginView.hideProgress();
        loginView.succededToLogin(loginResponseDTO);

    }

    @Override
    public void onLoginFailed(String message) {

        loginView.hideProgress();
        loginView.showError(message);
    }



}
