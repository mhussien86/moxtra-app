package com.madroid.moxtraapp.interactors.categories;

import com.madroid.moxtraapp.data.APIConstants;
import com.madroid.moxtraapp.data.ServiceGenerator;
import com.madroid.moxtraapp.data.api.CategoriesAPI;
import com.madroid.moxtraapp.dtos.categories.AllCategoriesResponseDTO;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mohamed on 25/03/17.
 */

public class CategoriesInteractorImpl implements CategoriesInteractor{



    CategoriesAPI categoriesAPI ;
    CompositeSubscription compositeSubscription ;

    public CategoriesInteractorImpl(){

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

                        onGetAllCategoriesFinished.onGetAllCategoriesFailed(""+e.getMessage());
                    }

                    @Override
                    public void onNext(AllCategoriesResponseDTO allCategoriesResponseDTO) {

                        onGetAllCategoriesFinished.onGetAllCategoriesSucceed(allCategoriesResponseDTO);
                    }


                }));

    }

    @Override
    public void unsubscribe() {

        if(compositeSubscription!=null && compositeSubscription.hasSubscriptions()){
            compositeSubscription.unsubscribe();
        }

    }

}
