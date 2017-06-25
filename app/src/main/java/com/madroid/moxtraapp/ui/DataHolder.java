package com.madroid.moxtraapp.ui;

/**
 * Created by omarmakeen on 6/24/17.
 */

public class DataHolder {
    private String token;
    public String getToken() {return token;}
    public void setToken(String data) {this.token = data;}

    private static final DataHolder holder = new DataHolder();
    public static DataHolder getInstance() {return holder;}
}
