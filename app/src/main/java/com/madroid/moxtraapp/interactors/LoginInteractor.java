package com.madroid.moxtraapp.interactors;

import com.madroid.moxtraapp.dtos.LoginRequestDTO;
import com.madroid.moxtraapp.dtos.LoginResponseDTO;

/**
 * Created by mohamed on 13/01/17.
 */
public interface LoginInteractor {


    void login(LoginRequestDTO loginRequestDTO, OnLoginFinished onLoginFinished);

    interface OnLoginFinished{

        void onLoginSucceed(LoginResponseDTO loginResponseDTO);
        void onLoginFailed(String message);


    }

    void unsubscribe();

}
