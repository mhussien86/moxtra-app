package com.madroid.moxtraapp.ui.timeline;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.madroid.moxtraapp.AppConstants;
import com.madroid.moxtraapp.BaseActivity;
import com.madroid.moxtraapp.BaseFragment;
import com.madroid.moxtraapp.R;
import com.madroid.moxtraapp.dtos.LoginResponseDTO;
import com.madroid.moxtraapp.dtos.binders.BindersResponseDTO;
import com.madroid.moxtraapp.ui.meet.MeetingsContainerActivity;
import com.moxtra.binder.sdk.InviteToChatCallback;
import com.moxtra.binder.sdk.MXException;
import com.moxtra.binder.sdk.OnEndMeetListener;
import com.moxtra.sdk.MXChatCustomizer;
import com.moxtra.sdk.MXChatManager;
import com.moxtra.sdk.MXGroupChatSession;
import com.moxtra.sdk.MXGroupChatSessionCallback;
import com.moxtra.sdk.MXSDKException;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import utils.PreferencesUtils;

/**
 * Created by mohamed on 16/01/17.
 */
public class TimelineListFragment extends BaseFragment implements TimelineListView {


    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;

    TimelineListAdapter contactsListAdapter;

    TimeLinePresenter timelinePresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_timeline_list, container, false);
        unbinder = ButterKnife.bind(this, rootView);
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
        timelinePresenter = new com.madroid.moxtraapp.ui.timeline.TimelinePresenterImpl(this);
        String accessToken = PreferencesUtils.getInstance(getActivity()).getString(AppConstants.ACCESS_TOKEN);
        timelinePresenter.getAllBinders(accessToken, "all", null);
    }

    private void showAllConversations(List<BindersResponseDTO.Binder> sessions) {

//        List<MXGroupChatSession> sessions = MXChatManager.getInstance().getGroupChatSessions();

        contactsListAdapter = new TimelineListAdapter(getContext(), sessions, new TimelineListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BindersResponseDTO.Binder_ session) {

//                if (session.isAChat()) {
                try {
                    MXChatManager.getInstance().openChat(session.getId(), new MXChatManager.OnOpenChatListener() {
                        @Override
                        public void onOpenChatSuccess() {

                            Log.e("open chat", "open moxtra chat success");
                        }

                        @Override
                        public void onOpenChatFailed(int i, String s) {
                            Log.e("open chat", s);
                        }
                    });
                } catch (MXException.AccountManagerIsNotValid accountManagerIsNotValid) {


                }
//                } else if (session.isAMeet()) {
//                    joinMeet(session);
//                }

            }
        });

        recyclerView.setAdapter(contactsListAdapter);


        MXChatCustomizer.setOnMeetEndListener(new OnEndMeetListener() {
            @Override
            public void onMeetEnded(String meetId) {
//                contactsListAdapter.refreshData();
            }

            @Override
            public void onMeetEndFailed(int errorCode, String errorMessage) {
                Log.e(TAG, "onMeetEndFailed() called with: " + "errorCode = [" + errorCode + "], errorMessage = [" + errorMessage + "]");
            }
        });

        MXChatManager.getInstance().setGroupChatSessionCallback(new MXGroupChatSessionCallback() {
            @Override
            public void onGroupChatSessionCreated(MXGroupChatSession session) {
//                contactsListAdapter.refreshData();
            }

            @Override
            public void onGroupChatSessionUpdated(MXGroupChatSession session) {
//                contactsListAdapter.refreshData();
            }

            @Override
            public void onGroupChatSessionDeleted(MXGroupChatSession session) {
//                contactsListAdapter.refreshData();
            }
        });

        MXChatCustomizer.setChatInviteCallback(new InviteToChatCallback() {
            @Override
            public void onInviteToChat(String binderID, Bundle extras) {
                Log.d(TAG, "Invite to binder: " + binderID);
            }
        });
    }

    String TAG = "MXGroupChatSession";

    private void joinMeet(MXGroupChatSession session) {
        if (!MXChatManager.getInstance().isAMeetingInProgress()) {
            try {

                //TODO add user name
                MXChatManager.getInstance().joinMeet(session.getMeetID(), "Hello",
                        new MXChatManager.OnJoinMeetListener() {
                            @Override
                            public void onJoinMeetDone(String meetId, String meetUrl) {
                                Log.d(TAG, "Joined meet: " + meetId);
                            }

                            @Override
                            public void onJoinMeetFailed() {
                                Log.e(TAG, "Unable to join meet.");
                            }
                        });
            } catch (MXSDKException.MeetIsInProgress meetIsInProgress) {
                Log.e(TAG, "Error when join meet", meetIsInProgress);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        contactsListAdapter.refreshData();
    }

    // display popup window with custom view
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


    private void setUpToolBar() {
        //add the Toolbar
        //Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);

        LayoutInflater mInflater = LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.timeline_toolbar, null);
        toolbar.addView(mCustomView);
        TextView filter = ((TextView) toolbar.findViewById(R.id.filter));

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFilterPopup(view, R.menu.popup_filters);
            }
        });

        ImageView add = (ImageView) toolbar.findViewById(R.id.icon_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showFilterPopup(view, R.menu.popup_new_action);
            }
        });

        ((BaseActivity) getActivity()).setSupportActionBar(toolbar);
        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    // Display anchored popup menu based on view selected
    private void showFilterPopup(View v, int resource) {
        PopupMenu popup = new PopupMenu(getActivity(), v);
        // Inflate the menu from xml
        popup.getMenuInflater().inflate(resource, popup.getMenu());

        if (resource == R.menu.popup_filters) {
            // Setup menu item selection
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    String accessToken = PreferencesUtils.getInstance(getActivity()).getString(AppConstants.ACCESS_TOKEN);

                    switch (item.getItemId()) {
                        case R.id.menu_all:
//                            showAllConversations();
                            timelinePresenter.getAllBinders(accessToken, "all", null);
                            return true;
                        case R.id.menu_favorite:
                            timelinePresenter.getFavoriteBinders(accessToken, "favorite", null);
                            Toast.makeText(getActivity(), "Popularity!", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.menu_unread:
                            timelinePresenter.getUnReadBinders(accessToken, "unread", null);
                            Toast.makeText(getActivity(), "menu_unread!", Toast.LENGTH_SHORT).show();
                            return true;
                        default:
                            return false;
                    }
                }
            });
        } else if (resource == R.menu.popup_new_action) {
            // Setup menu item selection
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.menu_meet_now:
                            getActivity().startActivity(new Intent(new Intent(getActivity(), MeetingsContainerActivity.class)).putExtra("data", "start"));
                            return true;
                        case R.id.menu_group_conversation:
                            Toast.makeText(getActivity(), "menu_group_conversation!", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.menu_direct_message:
                            try {
                                Bundle b = getActivity().getIntent().getBundleExtra("bundle");
                                LoginResponseDTO loginResponseDTO = Parcels.unwrap(b.getParcelable(AppConstants.LOGIN_RESPONSE));
                                Intent intent = new Intent(getActivity(), ContactsListActivity.class);
                                b.putParcelable(AppConstants.LOGIN_RESPONSE, Parcels.wrap(loginResponseDTO));
                                intent.putExtra("bundle", b);
                                startActivity(intent);
                            } catch (Exception e) {

                            }
                            return true;
                        default:
                            return false;
                    }
                }
            });


        }
        MenuPopupHelper menuHelper = new MenuPopupHelper(getActivity(), (MenuBuilder) popup.getMenu(), v);
        menuHelper.setForceShowIcon(true);
        menuHelper.setGravity(Gravity.CENTER);
        menuHelper.show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void updateListWithFavorites(BindersResponseDTO bindersResponseDTO) {

        showAllConversations(bindersResponseDTO.getData().getBinders());
    }

    @Override
    public void updateListWithUnread(BindersResponseDTO bindersResponseDTO) {
        showAllConversations(bindersResponseDTO.getData().getBinders());

    }

    @Override
    public void updateListWithAll(BindersResponseDTO bindersResponseDTO) {

        Log.e("size", "" + bindersResponseDTO.getData().getBinders().size());
        showAllConversations(bindersResponseDTO.getData().getBinders());

    }

}
