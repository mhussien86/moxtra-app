package com.madroid.moxtraapp.ui.binders_add;

import com.madroid.moxtraapp.dtos.categories.BindersAddCategoryRequestDTO;
import com.madroid.moxtraapp.dtos.simple.SimpleResponseDTO;
import com.madroid.moxtraapp.interactors.categories.CategoriesInteractor;
import com.madroid.moxtraapp.interactors.categories.CategoriesInteractorImpl;

/**
 * Created by Omar Makeen on 22/06/17.
 */

public class BindersAddPresenterImpl implements BindersAddPresenter, CategoriesInteractor.OnAssignBindersToCategoryFinished {


    private final BindersAddView bindersAddView;
    CategoriesInteractor categoriesInteractor;

    public BindersAddPresenterImpl(BindersAddView bindersAddView) {
        this.bindersAddView = bindersAddView;
        categoriesInteractor = new CategoriesInteractorImpl();
    }

    @Override
    public void assignBindersToCategory(Integer categoryId, String access_token,  BindersAddCategoryRequestDTO body) {
        bindersAddView.showProgress();
        categoriesInteractor.assignBindersToCategory(categoryId, access_token, body, this);
    }

    @Override
    public void onAssignBindersToCategorySucceed(SimpleResponseDTO responseDTO) {
        bindersAddView.hideProgress();
        bindersAddView.setAssignBindersToCategory(responseDTO);
    }

    @Override
    public void onAssignBindersToCategoryFailed(String message) {
        bindersAddView.hideProgress();
        bindersAddView.showError(message);
    }


}
