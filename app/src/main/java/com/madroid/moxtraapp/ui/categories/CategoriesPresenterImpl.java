package com.madroid.moxtraapp.ui.categories;

import com.madroid.moxtraapp.dtos.catergoriesandbinders.CategoriesAndBindersDTO;
import com.madroid.moxtraapp.interactors.categoriesbindersinteractors.CategoriesBindersInteractor;
import com.madroid.moxtraapp.interactors.categoriesbindersinteractors.CategoriesBindersInteractorImpl;

/**
 * Created by mohamed on 13/03/17.
 */

public class CategoriesPresenterImpl implements CategoriesPresenter, CategoriesBindersInteractor.OnFinished {


    private final CategoriesView categoriesView;
    CategoriesBindersInteractor categoriesBindersInteractor;

    public CategoriesPresenterImpl(CategoriesView categoriesView) {
        this.categoriesView = categoriesView;
        categoriesBindersInteractor = new CategoriesBindersInteractorImpl();
    }

    @Override
    public void getCategoriesAndBinders(String access_token, String filter, String sort) {
        categoriesView.showProgress();
        categoriesBindersInteractor.getCategoriesAndBinders(access_token, filter, sort, this);
    }

    @Override
    public void onGetCategoriesWithBindersSucceed(CategoriesAndBindersDTO categoriesAndBindersDTO) {
        categoriesView.hideProgress();
        categoriesView.setCategoriesAndBinders(categoriesAndBindersDTO);
    }

    @Override
    public void onGetCategoriesWithBindersFailed(String message) {
        categoriesView.hideProgress();
        categoriesView.showError(message);
    }

    @Override
    public void unsubscribe() {
        categoriesBindersInteractor.unsubscribe();
    }
}
