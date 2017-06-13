package com.madroid.moxtraapp.interactors.bindersinteractors;

import com.madroid.moxtraapp.data.APIConstants;
import com.madroid.moxtraapp.data.ServiceGenerator;
import com.madroid.moxtraapp.data.api.BindersAPI;
import com.madroid.moxtraapp.dtos.binders.BindersResponseDTO;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mohamed on 13/06/17.
 */

public class BindersInteractorImpl implements BindersInteractor {


    BindersAPI bindersAPI;
    CompositeSubscription compositeSubscription;

    public BindersInteractorImpl() {


        bindersAPI = new ServiceGenerator(APIConstants.API_SANDBOX).createService(BindersAPI.class);
        compositeSubscription = new CompositeSubscription();

    }

    @Override
    public void fetchAllBinders(String accecss_tokes, String filter, final onFetchBinders onFetchBinders) {

        Observable<BindersResponseDTO> observable = bindersAPI.getAllBinders(accecss_tokes, filter);

        compositeSubscription.add(observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BindersResponseDTO>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {

                        onFetchBinders.onFail("" + e.getMessage());
                    }

                    @Override
                    public void onNext(BindersResponseDTO bindersResponseDTO) {

                        onFetchBinders.onSucceedFetchAllBinders(bindersResponseDTO);
                    }


                }));

    }

    @Override
    public void fetchUnReadBinders(String accecss_tokes, String filter, final onFetchBinders onFetchBinders) {
        Observable<BindersResponseDTO> observable = bindersAPI.getAllBinders(accecss_tokes, filter);

        compositeSubscription.add(observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BindersResponseDTO>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {

                        onFetchBinders.onFail("" + e.getMessage());
                    }

                    @Override
                    public void onNext(BindersResponseDTO bindersResponseDTO) {

                        onFetchBinders.onSucceedFetchUnReadBinders(bindersResponseDTO);
                    }


                }));
    }

    @Override
    public void fetchFavoriteBinders(String accecss_tokes, String filter, final onFetchBinders onFetchBinders) {
        Observable<BindersResponseDTO> observable = bindersAPI.getAllBinders(accecss_tokes, filter);

        compositeSubscription.add(observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BindersResponseDTO>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {

                        onFetchBinders.onFail("" + e.getMessage());
                    }

                    @Override
                    public void onNext(BindersResponseDTO bindersResponseDTO) {

                        onFetchBinders.onSucceedFetchFavoriteBinders(bindersResponseDTO);
                    }


                }));
    }

    @Override
    public void unSubscribe() {

        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
        }
    }
}
