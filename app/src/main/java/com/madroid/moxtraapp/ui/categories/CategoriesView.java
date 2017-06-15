package com.madroid.moxtraapp.ui.categories;

import com.madroid.moxtraapp.dtos.catergoriesandbinders.CategoriesAndBindersDTO;

/**
 * Created by mohamed on 13/03/17.
 */

public interface CategoriesView {


    void setCategoriesAndBinders(CategoriesAndBindersDTO categoriesAndBindersList);

    void showProgress();

    void hideProgress();

    void showError(String message);

}
