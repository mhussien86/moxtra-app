package com.madroid.moxtraapp.ui.meet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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

/**
 * Created by mohamed on 08/05/17.
 */

public class JoinMeetingFragment extends BaseFragment {


    @Bind(R.id.et_meet_number)
    EditText meetingName;

    @Bind(R.id.switch_audio_on)
    BrandingSwitch switchAudio;

    @Bind(R.id.switch_video_on)
    BrandingSwitch switchVideo;

    @Bind(R.id.btn_action)
    BrandingStateButton actionButton;

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_join_meet, container, false);
        ButterKnife.bind(this, view);



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

    }




}
