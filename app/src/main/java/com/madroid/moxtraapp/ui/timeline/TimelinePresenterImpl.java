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
    public void getAllBinders() {

    }

    @Override
    public void getUnReadBinders() {

    }

    @Override
    public void getFavoriteBinders() {

    }
}
