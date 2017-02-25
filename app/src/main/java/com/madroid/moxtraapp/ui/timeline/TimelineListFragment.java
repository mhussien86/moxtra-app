package com.madroid.moxtraapp.ui.timeline;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_contact_list, container, false);
        ButterKnife.bind(this, rootView);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
