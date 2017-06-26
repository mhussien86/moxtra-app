package com.madroid.moxtraapp.ui.categories_create;

import com.google.gson.JsonObject;

/**
 * Created by mohamed on 13/03/17.
 */

public interface CreateCategoryPresenter {

    void createCategory(String access_token, JsonObject category_name);

}
