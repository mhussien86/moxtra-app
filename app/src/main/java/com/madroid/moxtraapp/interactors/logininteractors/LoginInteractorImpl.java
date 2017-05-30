package com.madroid.moxtraapp.interactors.logininteractors;

import com.madroid.moxtraapp.data.ServiceGenerator;
import com.madroid.moxtraapp.data.api.AccountManagement;
import com.madroid.moxtraapp.dtos.LoginRequestDTO;
import com.madroid.moxtraapp.dtos.LoginResponseDTO;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by mohamed on 13/01/17.
 */
public class LoginInteractorImpl implements LoginInteractor{


    AccountManagement accountManagement ;
    CompositeSubscription compositeSubscription ;

    public LoginInteractorImpl(){

        accountManagement = new ServiceGenerator().createService(AccountManagement.class);
        compositeSubscription = new CompositeSubscription();
    }


    @Override
    public void login(LoginRequestDTO loginRequestDTO, final OnLoginFinished onLoginFinished) {

        Observable<LoginResponseDTO> observable = accountManagement.loginUser(loginRequestDTO);

        compositeSubscription.add(observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResponseDTO>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {

                        onLoginFinished.onLoginFailed(""+e.getMessage());
                    }

                    @Override
                    public void onNext(LoginResponseDTO loginResponseDTO) {

                        onLoginFinished.onLoginSucceed(loginResponseDTO);
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
