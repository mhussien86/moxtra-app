package com.madroid.moxtraapp.ui.timeline;

/**
 * Created by mohamed on 16/01/17.
 */
public interface TimelineListView {

    void showLoading();
    void hideLoading();
    void showError();
    void updateListWithFavorites();
    void updateListWithUnread();
    void updateListWithAll();


}
