package com.madroid.moxtraapp.data.api;

import com.madroid.moxtraapp.dtos.LoginRequestDTO;
import com.madroid.moxtraapp.dtos.LoginResponseDTO;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by mohamed on 18/12/16.
 */
public interface AccountManagement {

    @POST("user/login")
    Observable<LoginResponseDTO> loginUser(@Body LoginRequestDTO loginRequestDTO);

}
