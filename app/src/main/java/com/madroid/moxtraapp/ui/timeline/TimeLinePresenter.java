package com.madroid.moxtraapp.ui.timeline;

/**
 * Created by mohamed on 10/06/17.
 */

public interface TimeLinePresenter {


    void getAllBinders(String access_token, String filter, String sort);

    void getUnReadBinders(String access_token, String filter, String sort);

    void getFavoriteBinders(String access_token, String filter, String sort);

    void onDestroy();

}
