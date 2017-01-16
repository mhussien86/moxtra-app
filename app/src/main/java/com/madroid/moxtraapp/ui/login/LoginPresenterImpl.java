package com.madroid.moxtraapp.ui.login;

import com.madroid.moxtraapp.dtos.LoginRequestDTO;
import com.madroid.moxtraapp.interactors.LoginInteractor;
import com.madroid.moxtraapp.interactors.LoginInteractorImpl;

/**
 * Created by mohamed on 15/01/17.
 */
public class LoginPresenterImpl implements LoginPresenter{

    private LoginView loginView;
    private LoginInteractor loginInteractor ;

    public LoginPresenterImpl(LoginView loginView){
        this.loginView = loginView ;
        loginInteractor = new LoginInteractorImpl();

    }


    @Override
    public void login(LoginRequestDTO loginRequestDTO) {




    }

    @Override
    public void onDestroy() {



    }
}
