package com.madroid.moxtraapp.ui.timeline;

import com.madroid.moxtraapp.interactors.bindersinteractors.BindersInteractor;
import com.madroid.moxtraapp.interactors.bindersinteractors.BindersInteractorImpl;

/**
 * Created by mohamed on 10/06/17.
 */

public class TimelinePresenterImpl implements TimeLinePresenter {


    private BindersInteractor bindersInteractor ;
    private TimelineListView timelineListView ;

    public TimelinePresenterImpl(TimelineListView timelineListView){

        this.timelineListView = timelineListView;
        bindersInteractor = new BindersInteractorImpl();
    }


    @Override
    public void getAllBinders(String access_token, String filter) {

    }

    @Override
    public void getUnReadBinders(String access_token, String filter) {

    }

    @Override
    public void getFavoriteBinders(String access_token, String filter) {

    }

    @Override
    public void onDestroy() {

    }
}
