package com.madroid.moxtraapp.interactors.categoriesbindersinteractors;

import com.madroid.moxtraapp.dtos.catergoriesandbinders.CategoriesAndBindersDTO;

/**
 * Created by mohamed on 25/03/17.
 */

public interface CategoriesBindersInteractor {

    void getCategoriesAndBinders(String accecss_tokes, String filter,String sort, OnFinished onFinished);

    interface OnFinished {
        void onGetCategoriesWithBindersSucceed(CategoriesAndBindersDTO categoriesAndBindersDTO);
        void onGetCategoriesWithBindersFailed(String message);
    }

    void unsubscribe();

}
