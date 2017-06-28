package com.madroid.moxtraapp.ui.categories_create;

import com.google.gson.JsonObject;
import com.madroid.moxtraapp.dtos.simple.SimpleResponseDTO;
import com.madroid.moxtraapp.interactors.categories.CategoriesInteractor;
import com.madroid.moxtraapp.interactors.categories.CategoriesInteractorImpl;

/**
 * Created by Omar Makeen on 22/06/17.
 */

public class CreateCategoryPresenterImpl implements CreateCategoryPresenter, CategoriesInteractor.OnCreateCategoryFinished {


    private final CreateCategoryView categoryView;
    CategoriesInteractor categoriesInteractor;

    public CreateCategoryPresenterImpl(CreateCategoryView categoryView) {
        this.categoryView = categoryView;
        categoriesInteractor = new CategoriesInteractorImpl();
    }

    @Override
    public void createCategory(String access_token, JsonObject category_name) {
        categoryView.showProgress();
        categoriesInteractor.createCategory(access_token, category_name, this);
    }

    @Override
    public void unsubscribe() {
        categoriesInteractor.unsubscribe();
    }

    @Override
    public void onCreateCategorySucceed(SimpleResponseDTO responseDTO) {
        categoryView.hideProgress();
        categoryView.setCreateCategory(responseDTO);
    }

    @Override
    public void onCreateCategoryFailed(String message) {
        categoryView.hideProgress();
        categoryView.showError(message);
    }


}
