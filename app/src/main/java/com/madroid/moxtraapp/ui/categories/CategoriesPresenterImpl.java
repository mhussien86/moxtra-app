package com.madroid.moxtraapp.ui.categories;
import com.madroid.moxtraapp.dtos.categories.AllCategoriesResponseDTO;
import com.madroid.moxtraapp.interactors.categories.CategoriesInteractor;
import com.madroid.moxtraapp.interactors.categories.CategoriesInteractorImpl;

/**
 * Created by mohamed on 13/03/17.
 */

public class CategoriesPresenterImpl implements CategoriesPresenter, CategoriesInteractor.OnGetAllCategoriesFinished {


    private final CategoriesView categoriesView;
    CategoriesInteractor categoriesInteractor ;

    public CategoriesPresenterImpl(CategoriesView categoriesView){
        this.categoriesView = categoriesView ;
        categoriesInteractor = new CategoriesInteractorImpl();
    }

    @Override
    public void getAllCategories(String access_token) {

        categoriesView.showProgress();
        categoriesInteractor.getAllCategories(access_token,this);
    }

    @Override
    public void onGetAllCategoriesSucceed(AllCategoriesResponseDTO allCategoriesResponseDTO) {

        categoriesView.hideProgress();
        categoriesView.setAllCategories(allCategoriesResponseDTO);
    }

    @Override
    public void onGetAllCategoriesFailed(String message) {
        categoriesView.hideProgress();
        categoriesView.showError(message);

    }
}
