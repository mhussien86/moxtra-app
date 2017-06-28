package com.madroid.moxtraapp.interactors.categories;

import com.google.gson.JsonObject;
import com.madroid.moxtraapp.data.APIConstants;
import com.madroid.moxtraapp.data.ServiceGenerator;
import com.madroid.moxtraapp.data.api.CategoriesAPI;
import com.madroid.moxtraapp.dtos.categories.AllCategoriesResponseDTO;
import com.madroid.moxtraapp.dtos.categories.BindersAddCategoryRequestDTO;
import com.madroid.moxtraapp.dtos.simple.SimpleResponseDTO;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mohamed on 25/03/17.
 */

public class CategoriesInteractorImpl implements CategoriesInteractor {


    CategoriesAPI categoriesAPI;
    CompositeSubscription compositeSubscription;

    public CategoriesInteractorImpl() {

        categoriesAPI = new ServiceGenerator(APIConstants.API_SANDBOX).createService(CategoriesAPI.class);
        compositeSubscription = new CompositeSubscription();
    }


    @Override
    public void getAllCategories(String accecss_tokes, final OnGetAllCategoriesFinished onGetAllCategoriesFinished) {

        Observable<AllCategoriesResponseDTO> observable = categoriesAPI.getAllCategories(accecss_tokes);

        compositeSubscription.add(observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AllCategoriesResponseDTO>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {

                        onGetAllCategoriesFinished.onGetAllCategoriesFailed("" + e.getMessage());
                    }

                    @Override
                    public void onNext(AllCategoriesResponseDTO allCategoriesResponseDTO) {

                        onGetAllCategoriesFinished.onGetAllCategoriesSucceed(allCategoriesResponseDTO);
                    }


                }));

    }


    @Override
    public void assignBindersToCategory(Integer categoryId, String accecss_tokes, BindersAddCategoryRequestDTO body, final OnAssignBindersToCategoryFinished onAssignBindersToCategoryFinished) {

        Observable<SimpleResponseDTO> observable = categoriesAPI.assignBindersToCategory(categoryId, accecss_tokes, body);

        compositeSubscription.add(observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SimpleResponseDTO>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {

                        onAssignBindersToCategoryFinished.onAssignBindersToCategoryFailed("" + e.getMessage());
                    }

                    @Override
                    public void onNext(SimpleResponseDTO responseDTO) {

                        onAssignBindersToCategoryFinished.onAssignBindersToCategorySucceed(responseDTO);
                    }


                }));

    }

    @Override
    public void createCategory(String accecss_tokes, JsonObject category_name, final OnCreateCategoryFinished onCreateCategoryFinished) {

        Observable<SimpleResponseDTO> observable = categoriesAPI.createCategory(accecss_tokes, category_name);

        compositeSubscription.add(observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SimpleResponseDTO>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {

                        onCreateCategoryFinished.onCreateCategoryFailed("" + e.getMessage());
                    }

                    @Override
                    public void onNext(SimpleResponseDTO responseDTO) {

                        onCreateCategoryFinished.onCreateCategorySucceed(responseDTO);
                    }


                }));

    }

    @Override
    public void renameCategory(String accecss_tokes, JsonObject category, final OnRenameCategoryFinished onRenameCategoryFinished) {

        Observable<SimpleResponseDTO> observable = categoriesAPI.renameCategory(accecss_tokes, category);

        compositeSubscription.add(observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SimpleResponseDTO>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {

                        onRenameCategoryFinished.onRenameCategoryFailed("" + e.getMessage());

                    }

                    @Override
                    public void onNext(SimpleResponseDTO responseDTO) {

                        onRenameCategoryFinished.onRenameCategorySucceed(responseDTO);

                    }


                }));

    }


    @Override
    public void deleteCategory(Integer categoryId, String accecss_tokes, final OnDeleteCategoryFinished onDeleteCategoryFinished) {

        Observable<SimpleResponseDTO> observable = categoriesAPI.deleteCategory(categoryId, accecss_tokes);

        compositeSubscription.add(observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SimpleResponseDTO>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {

                        onDeleteCategoryFinished.onDeleteCategoryFailed("" + e.getMessage());


                    }

                    @Override
                    public void onNext(SimpleResponseDTO responseDTO) {

                        onDeleteCategoryFinished.onDeleteCategorySucceed(responseDTO);


                    }


                }));

    }

    @Override
    public void unsubscribe() {

        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
        }

    }

}
