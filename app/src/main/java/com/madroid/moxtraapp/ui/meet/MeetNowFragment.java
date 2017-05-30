package com.madroid.moxtraapp.ui.meet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.madroid.moxtraapp.AppConstants;
import com.madroid.moxtraapp.BaseActivity;
import com.madroid.moxtraapp.BaseFragment;
import com.madroid.moxtraapp.R;
import com.moxtra.binder.ui.branding.widget.BrandingStateButton;
import com.moxtra.binder.ui.branding.widget.BrandingSwitch;
import com.moxtra.isdk.util.Log;
import com.moxtra.sdk.MXChatManager;
import com.moxtra.sdk.MXSDKException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.PreferencesUtils;

/**
 * Created by mohamed on 08/05/17.
 */

public class MeetNowFragment extends BaseFragment {


    @Bind(R.id.et_meet_number)
    EditText meetingName;

    @Bind(R.id.switch_audio_on)
    BrandingSwitch switchAudio;

    @Bind(R.id.switch_video_on)
    BrandingSwitch switchVideo;

    @Bind(R.id.btn_action)
    BrandingStateButton actionButton;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_meet_now, container, false);
        ButterKnife.bind(this, view);
        setUpToolBar("Meet Now");
        meetingName.setText(PreferencesUtils.getInstance(getActivity()).getString(AppConstants.USER_NAME)+"'s Meet");

        return view;
    }


    @OnClick(R.id.btn_action)
    public void startMeeting(View view){

        try {
            MXChatManager.getInstance().startMeet(meetingName.getText() + "'s meet", null,
                    null, new MXChatManager.OnStartMeetListener() {
                        @Override
                        public void onStartMeetDone(String meetId, String meetUrl) {
                            Log.d("Meeting", "Meet started: " + meetId);
                        }

                        @Override
                        public void onStartMeetFailed(int i, String s) {
                            Log.e("Meeting", "onStartMeetFailed: " + s);
                        }
                    });
        } catch (MXSDKException.Unauthorized unauthorized) {
            Log.e("Meeting", "Error when start meet" + unauthorized);
        } catch (MXSDKException.MeetIsInProgress meetIsInProgress) {
            Log.e("Meeting", "Error when start meet"+ meetIsInProgress);
        }

        getActivity().finish();
    }

    private void setUpToolBar(String pageTitle){
        //add the Toolbar
        //Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);

        LayoutInflater mInflater=LayoutInflater.from(getActivity());
        View mCustomView = mInflater.inflate(R.layout.categories_toolbar, null);
        toolbar.addView(mCustomView);
        TextView title =((TextView)toolbar.findViewById(R.id.title));
        title.setText(pageTitle);
        ((ImageView) toolbar.findViewById(R.id.icon_add)).setVisibility(View.INVISIBLE);
        ((BaseActivity)getActivity()).setSupportActionBar(toolbar);
        ((BaseActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((BaseActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

    }


}
