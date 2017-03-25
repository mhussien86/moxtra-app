package com.madroid.moxtraapp.interactors.categories;

import com.madroid.moxtraapp.dtos.categories.AllCategoriesResponseDTO;

/**
 * Created by mohamed on 25/03/17.
 */

public interface CategoriesInteractor {

    void getAllCategories(String accecss_tokes, OnGetAllCategoriesFinished onGetAllCategoriesFinished);

    interface OnGetAllCategoriesFinished{

        void onGetAllCategoriesSucceed(AllCategoriesResponseDTO allCategoriesResponseDTO);
        void onGetAllCategoriesFailed(String message);
    }

    void unsubscribe();

}
