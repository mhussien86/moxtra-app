package com.madroid.moxtraapp.ui.timeline;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import com.madroid.moxtraapp.BaseActivity;
import com.madroid.moxtraapp.BaseFragment;
import com.madroid.moxtraapp.R;
import com.moxtra.binder.sdk.MXException;
import com.moxtra.sdk.MXChatManager;
import com.moxtra.sdk.MXGroupChatSession;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mohamed on 16/01/17.
 */
public class TimelineListFragment extends BaseFragment {


    @Bind(R.id.recycle_view)
    RecyclerView recyclerView;

    TimelineListAdapter contactsListAdapter;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contact_list, container, false);
        ButterKnife.bind(this, rootView);
        setUpToolBar();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);

        List<MXGroupChatSession> sessions = MXChatManager.getInstance().getGroupChatSessions();

        contactsListAdapter = new TimelineListAdapter(sessions, new TimelineListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MXGroupChatSession session) {
                try {
                    MXChatManager.getInstance().openChat(session.getSessionID(), new MXChatManager.OnOpenChatListener() {
                        @Override
                        public void onOpenChatSuccess() {

                        }

                        @Override
                        public void onOpenChatFailed(int i, String s) {

                        }
                    });
                }catch (MXException.AccountManagerIsNotValid accountManagerIsNotValid){

                }

            }
        });



        recyclerView.setAdapter(contactsListAdapter);

//        MXChatCustomizer.setOnMeetEndListener(new OnEndMeetListener() {
//            @Override
//            public void onMeetEnded(String meetId) {
//                contactsListAdapter.refreshData();
//            }
//
//            @Override
//            public void onMeetEndFailed(int errorCode, String errorMessage) {
//                Log.e("On meet end", "onMeetEndFailed() called with: " + "errorCode = [" + errorCode + "], errorMessage = [" + errorMessage + "]");
//            }
//        });
//        MXChatManager.getInstance().setGroupChatSessionCallback(new MXGroupChatSessionCallback() {
//            @Override
//            public void onGroupChatSessionCreated(MXGroupChatSession session) {
//                contactsListAdapter.refreshData();
//            }
//
//            @Override
//            public void onGroupChatSessionUpdated(MXGroupChatSession session) {
//                contactsListAdapter.refreshData();
//            }
//
//            @Override
//            public void onGroupChatSessionDeleted(MXGroupChatSession session) {
//                contactsListAdapter.refreshData();
//            }
//        });
    }


    private void displayPopupWindow(View anchorView) {
        PopupWindow popup = new PopupWindow(getActivity());
        View layout = getActivity().getLayoutInflater().inflate(R.layout.activity_login, null);
        popup.setContentView(layout);
        // Set content width and height
        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        // Closes the popup window when touch outside of it - when looses focus
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        // Show anchored to button
        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.showAsDropDown(anchorView);
    }


    private void setUpToolBar(){
        //add the Toolbar
        //Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);

        LayoutInflater mInflater=LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.timeline_toolbar, null);
        toolbar.addView(mCustomView);
//        ImageView imgImpression=((ImageView)toolbar.findViewById(R.id.imgImpression));

        ((BaseActivity)getActivity()).setSupportActionBar(toolbar);
        ((BaseActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((BaseActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
