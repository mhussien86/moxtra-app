package com.madroid.moxtraapp.ui.timeline;

import com.madroid.moxtraapp.dtos.binders.BindersResponseDTO;
import com.madroid.moxtraapp.interactors.bindersinteractors.BindersInteractor;
import com.madroid.moxtraapp.interactors.bindersinteractors.BindersInteractorImpl;

/**
 * Created by mohamed on 10/06/17.
 */

public class TimelinePresenterImpl implements TimeLinePresenter, BindersInteractor.onFetchBinders {


    private BindersInteractor bindersInteractor ;
    private TimelineListView timelineListView ;

    public TimelinePresenterImpl(TimelineListView timelineListView){

        this.timelineListView = timelineListView;
        bindersInteractor = new BindersInteractorImpl();
    }


    @Override
    public void getAllBinders(String access_token, String filter) {

        timelineListView.showLoading();
        bindersInteractor.fetchAllBinders(access_token,filter,this);
    }

    @Override
    public void getUnReadBinders(String access_token, String filter) {
        timelineListView.showLoading();
        bindersInteractor.fetchUnReadBinders(access_token,filter,this);
    }

    @Override
    public void getFavoriteBinders(String access_token, String filter) {
        timelineListView.showLoading();

        bindersInteractor.fetchFavoriteBinders(access_token,filter,this);
    }

    @Override
    public void onDestroy() {

        timelineListView.hideLoading();
        bindersInteractor.unSubscribe();
        timelineListView=null;
    }

    @Override
    public void onSucceedFetchAllBinders(BindersResponseDTO bindersResponseDTO) {

        timelineListView.hideLoading();
        timelineListView.updateListWithAll(bindersResponseDTO);
    }

    @Override
    public void onSucceedFetchUnReadBinders(BindersResponseDTO bindersResponseDTO) {

        timelineListView.hideLoading();
        timelineListView.updateListWithUnread(bindersResponseDTO);
    }

    @Override
    public void onSucceedFetchFavoriteBinders(BindersResponseDTO bindersResponseDTO) {

        timelineListView.hideLoading();
        timelineListView.updateListWithFavorites(bindersResponseDTO);
    }

    @Override
    public void onFail(String errorMessage) {

    }
}
