package com.madroid.moxtraapp.ui.login;

import com.madroid.moxtraapp.dtos.LoginRequestDTO;

/**
 * Created by mohamed on 15/01/17.
 */
public interface LoginPresenter {

    void login(LoginRequestDTO loginRequestDTO);

    void onDestroy();

}
