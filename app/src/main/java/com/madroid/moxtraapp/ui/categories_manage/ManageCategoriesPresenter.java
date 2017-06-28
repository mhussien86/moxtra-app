package com.madroid.moxtraapp.ui.categories_manage;

import com.google.gson.JsonObject;

/**
 * Created by mohamed on 13/03/17.
 */

public interface ManageCategoriesPresenter {

    void renameCategory(String access_token, JsonObject category);

    void deleteCategory(Integer categoryId, String access_token);

    void unsubscribe();

}
