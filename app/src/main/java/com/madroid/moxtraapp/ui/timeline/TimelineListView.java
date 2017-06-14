package com.madroid.moxtraapp.ui.timeline;

import com.madroid.moxtraapp.dtos.binders.BindersResponseDTO;

/**
 * Created by mohamed on 16/01/17.
 */
public interface TimelineListView {

    void showLoading();
    void hideLoading();
    void showError(String message);
    void updateListWithFavorites(BindersResponseDTO bindersResponseDTO);
    void updateListWithUnread(BindersResponseDTO bindersResponseDTO);
    void updateListWithAll(BindersResponseDTO bindersResponseDTO);


}
