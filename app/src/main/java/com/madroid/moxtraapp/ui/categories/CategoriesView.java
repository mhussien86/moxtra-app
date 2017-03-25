package com.madroid.moxtraapp.ui.categories;

import com.madroid.moxtraapp.dtos.categories.AllCategoriesResponseDTO;

/**
 * Created by mohamed on 13/03/17.
 */

public interface CategoriesView {


    void setAllCategories(AllCategoriesResponseDTO allCategories);

    void showProgress();

    void hideProgress();

    void showError(String message);

}
