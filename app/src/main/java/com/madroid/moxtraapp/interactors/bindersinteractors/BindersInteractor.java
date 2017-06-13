package com.madroid.moxtraapp.interactors.bindersinteractors;

import com.madroid.moxtraapp.dtos.binders.BindersResponseDTO;

/**
 * Created by mohamed on 13/06/17.
 */

public interface BindersInteractor {


    void fetchAllBinders(String accecss_tokes,String filter, onFetchBinders onFetchBinders);
    void fetchUnReadBinders(String accecss_tokes,String filter, onFetchBinders onFetchBinders);
    void fetchFavoriteBinders(String accecss_tokes,String filter, onFetchBinders onFetchBinders);

    interface onFetchBinders{

        void onSucceedFetchAllBinders(BindersResponseDTO bindersResponseDTO);
        void onSucceedFetchUnReadBinders(BindersResponseDTO bindersResponseDTO);
        void onSucceedFetchFavoriteBinders(BindersResponseDTO bindersResponseDTO);
        void onFail(String errorMessage);

    }


    void unSubscribe();
}
