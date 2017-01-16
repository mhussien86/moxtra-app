package com.madroid.moxtraapp.ui.login;

import com.madroid.moxtraapp.dtos.LoginResponseDTO;

/**
 * Created by mohamed on 15/01/17.
 */
public interface LoginView {

    void showProgress();

    void hideProgress();

    void showError(String message);

    void succededToLogin(LoginResponseDTO loginResponseDTO);


}
