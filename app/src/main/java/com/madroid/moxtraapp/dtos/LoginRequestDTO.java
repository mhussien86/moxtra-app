package com.madroid.moxtraapp.dtos;

/**
 * Created by mohamed on 13/01/17.
 */
public class LoginRequestDTO {

    String username ;
    String password ;

    public LoginRequestDTO (String username, String password){

        this.username = username;
        this.password = password;

    }

}
