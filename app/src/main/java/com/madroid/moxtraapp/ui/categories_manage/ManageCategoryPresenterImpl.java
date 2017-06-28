package com.madroid.moxtraapp.ui.categories_manage;

import com.google.gson.JsonObject;
import com.madroid.moxtraapp.dtos.simple.SimpleResponseDTO;
import com.madroid.moxtraapp.interactors.categories.CategoriesInteractor;
import com.madroid.moxtraapp.interactors.categories.CategoriesInteractorImpl;

/**
 * Created by Omar Makeen on 22/06/17.
 */

public class ManageCategoryPresenterImpl implements ManageCategoriesPresenter, CategoriesInteractor.OnRenameCategoryFinished, CategoriesInteractor.OnDeleteCategoryFinished {


    private final ManageCategoriesView manageCategoriesView;
    CategoriesInteractor categoriesInteractor;

    public ManageCategoryPresenterImpl(ManageCategoriesView manageCategoriesView) {
        this.manageCategoriesView = manageCategoriesView;
        categoriesInteractor = new CategoriesInteractorImpl();
    }

//    @Override
//    public void createCategory(String access_token, JsonObject category_name) {
//        categoryView.showProgress();
//        categoriesInteractor.createCategory(access_token, category_name, this);
//    }

    @Override
    public void renameCategory(String access_token, JsonObject category) {
        manageCategoriesView.showProgress();
        categoriesInteractor.renameCategory(access_token, category, this);
    }

    @Override
    public void deleteCategory(Integer categoryId, String access_token) {
        manageCategoriesView.showProgress();
        categoriesInteractor.deleteCategory(categoryId, access_token, this);
    }

    @Override
    public void unsubscribe() {
        categoriesInteractor.unsubscribe();
    }


    @Override
    public void onRenameCategorySucceed(SimpleResponseDTO response) {
        manageCategoriesView.hideProgress();
        manageCategoriesView.setRenameCategory(response);
    }

    @Override
    public void onRenameCategoryFailed(String message) {
        manageCategoriesView.hideProgress();
        manageCategoriesView.showError(message);
    }

    @Override
    public void onDeleteCategorySucceed(SimpleResponseDTO response) {
        manageCategoriesView.hideProgress();
        manageCategoriesView.setDeleteCategory(response);
    }

    @Override
    public void onDeleteCategoryFailed(String message) {
        manageCategoriesView.hideProgress();
        manageCategoriesView.showError(message);
    }
}
