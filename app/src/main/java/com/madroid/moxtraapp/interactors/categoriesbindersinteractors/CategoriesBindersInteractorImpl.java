package com.madroid.moxtraapp.interactors.categoriesbindersinteractors;

import com.madroid.moxtraapp.data.APIConstants;
import com.madroid.moxtraapp.data.ServiceGenerator;
import com.madroid.moxtraapp.data.api.BindersAPI;
import com.madroid.moxtraapp.data.api.CategoriesAPI;
import com.madroid.moxtraapp.dtos.binders.BindersResponseDTO;
import com.madroid.moxtraapp.dtos.categories.AllCategoriesResponseDTO;
import com.madroid.moxtraapp.dtos.catergoriesandbinders.CategoriesAndBindersDTO;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mohamed on 25/03/17.
 */

public class CategoriesBindersInteractorImpl implements CategoriesBindersInteractor {



    CategoriesAPI categoriesAPI ;
    BindersAPI bindersAPI;
    CompositeSubscription compositeSubscription ;

    public CategoriesBindersInteractorImpl(){

        categoriesAPI = new ServiceGenerator(APIConstants.API_SANDBOX).createService(CategoriesAPI.class);
        bindersAPI = new ServiceGenerator(APIConstants.API_SANDBOX).createService(BindersAPI.class);
        compositeSubscription = new CompositeSubscription();
    }



    @Override
    public void getCategoriesAndBinders(String accecss_tokes, String filter, String sort, final OnFinished onFinished) {

        Observable<AllCategoriesResponseDTO> categoriesObservable = categoriesAPI.getAllCategories(accecss_tokes);
        Observable<BindersResponseDTO> bindersObservable = bindersAPI.getAllBinders(accecss_tokes, filter, sort);
        Observable<CategoriesAndBindersDTO> combined = Observable.zip(categoriesObservable, bindersObservable, new Func2<AllCategoriesResponseDTO, BindersResponseDTO, CategoriesAndBindersDTO>() {
            @Override
            public CategoriesAndBindersDTO call(AllCategoriesResponseDTO allCategoriesResponseDTO, BindersResponseDTO bindersResponseDTO) {
                return new CategoriesAndBindersDTO(allCategoriesResponseDTO, bindersResponseDTO);
            }
        });

        compositeSubscription.add(combined
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CategoriesAndBindersDTO>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {

                        onFinished.onGetCategoriesWithBindersFailed(""+e.getMessage());
                    }

                    @Override
                    public void onNext(CategoriesAndBindersDTO categoriesAndBindersDTO) {

                        onFinished.onGetCategoriesWithBindersSucceed(categoriesAndBindersDTO);
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


