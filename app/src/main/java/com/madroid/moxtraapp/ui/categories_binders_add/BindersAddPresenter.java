package com.madroid.moxtraapp.ui.categories_binders_add;

import com.madroid.moxtraapp.dtos.categories.BindersAddCategoryRequestDTO;

/**
 * Created by mohamed on 13/03/17.
 */

public interface BindersAddPresenter {

    void assignBindersToCategory(Integer categoryId, String access_token, BindersAddCategoryRequestDTO body);

}
